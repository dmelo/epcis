package org.oliot.epcis.service.capture;

import java.util.Iterator;

import javax.servlet.ServletContext;

import org.apache.log4j.Level;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.oliot.epcis.configuration.Configuration;
import org.oliot.epcis.service.capture.mongodb.MongoCaptureUtil;
import org.oliot.model.jsonschema.JsonSchemaLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

/**
 * Copyright (C) 2015 Jaewook Jack Byun
 *
 * This project is part of Oliot (oliot.org), pursuing the implementation of
 * Electronic Product Code Information Service(EPCIS) v1.1 specification in
 * EPCglobal.
 * [http://www.gs1.org/gsmp/kc/epcglobal/epcis/epcis_1_1-standard-20140520.pdf]
 * 
 *
 * @author Jaewook Jack Byun, Ph.D student
 * 
 *         Korea Advanced Institute of Science and Technology (KAIST)
 * 
 *         Real-time Embedded System Laboratory(RESL)
 * 
 *         bjw0829@kaist.ac.kr, bjw0829@gmail.com
 * 
 * @author Sungpil Woo, Master student
 * 
 *         Korea Advanced Institute of Science and Technology (KAIST)
 * 
 *         Real-time Embedded System Laboratory(RESL)
 * 
 *         woosungpil@kaist.ac.kr, woosungpil7@gmail.com
 */

@Controller
@RequestMapping("/JsonEventCapture")
public class JsonEventCapture implements ServletContextAware {

	@Autowired
	ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public String asyncPost(String inputString) {
		String result = post(inputString);
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String post(@RequestBody String inputString) {
		Configuration.logger.info(" EPCIS Json Document Capture Started.... ");

		if (Configuration.isCaptureVerfificationOn == true) {

			// JSONParser parser = new JSONParser();
			JsonSchemaLoader schemaLoader = new JsonSchemaLoader();

			try {

				JSONObject jsonEvent = new JSONObject(inputString);
				JSONObject jsonEventSchema = schemaLoader.getEventSchema();

				if (!CaptureUtil.validate(jsonEvent, jsonEventSchema)) {
					Configuration.logger.info("Json Document is invalid" + " about general_validcheck");
					return "Error: Json Document is not valid" + "general_validcheck";
				}
				JSONArray jsonEventList = jsonEvent.getJSONObject("epcis").getJSONObject("EPCISBody")
						.getJSONArray("EventList");

				for (int i = 0; i < jsonEventList.length(); i++) {
					JSONObject jsonEventElement = jsonEventList.getJSONObject(i);

					if (jsonEventElement.has("ObjectEvent") == true) {

						/* startpoint of validation logic for ObjectEvent */
						JSONObject objectEventSchema = schemaLoader.getObjectEventSchema();
						JSONObject jsonObjectEvent = jsonEventElement.getJSONObject("ObjectEvent");

						if (!CaptureUtil.validate(jsonObjectEvent, objectEventSchema)) {
							Configuration.logger
									.info("Json Document is not valid" + " detail validation check for objectevent");
							return "Error: Json Document is not valid" + " for detail validation check for objectevent";
						}

						/* finish validation logic for ObjectEvent */
						if (!jsonObjectEvent.has("recordTime")) {
							jsonObjectEvent.put("recordTime", System.currentTimeMillis());
						}

						if (jsonObjectEvent.has("any")) {
							/* start finding namespace in the any field. */
							JSONObject anyobject = jsonObjectEvent.getJSONObject("any");
							String namespace = "";
							boolean namespace_flag = false;

							Iterator<String> keyIter_ns = anyobject.keys();
							while (keyIter_ns.hasNext()) {
								String temp = keyIter_ns.next();
								if (temp.substring(0, 1).equals("@")) {
									namespace_flag = true;
									namespace = temp.substring(1, temp.length());
								}
							}

							if (!namespace_flag) {
								Configuration.logger.info("Json Document doesn't have namespace in any field");
								return "Error: Json Document doesn't have namespace in any field"
										+ " for detail validation check for objectevent";

							}
							/* finish finding namespace in the any field. */

							/*
							 * Start Validation whether each component use
							 * correct name space
							 */;

							Iterator<String> keyIter = anyobject.keys();
							while (keyIter.hasNext()) {
								String temp = keyIter.next();

								if (!temp.contains(namespace)) {
									Configuration.logger.info("Json Document use invalid namespace in anyfield");
									return "Error: Json Document use invalid namespace in anyfield"
											+ " for detail validation check for objectevent";
								}
							}
							/*
							 * Finish validation whether each component use
							 * correct name space
							 */

						}

						if (Configuration.backend.equals("MongoDB")) {
							MongoCaptureUtil m = new MongoCaptureUtil();
							m.captureObjectEvent(jsonObjectEvent);
						}
					} else if (jsonEventElement.has("AggregationEvent") == true) {

						/*
						 * startpoint of validation logic for AggregationEvent
						 */
						JSONObject aggregationEventSchema = schemaLoader.getAggregationEventSchema();
						JSONObject jsonAggregationEvent = jsonEventElement.getJSONObject("AggregationEvent");

						if (!CaptureUtil.validate(jsonAggregationEvent, aggregationEventSchema)) {

							Configuration.logger.info(
									"Json Document is not valid" + " detail validation check for aggregationevent");
							return "Error: Json Document is not valid"
									+ " for detail validation check for aggregationevent";
						}
						/* finish validation logic for AggregationEvent */

						if (!jsonAggregationEvent.has("recordTime")) {
							jsonAggregationEvent.put("recordTime", System.currentTimeMillis());
						}

						if (jsonAggregationEvent.has("any")) {
							/* start finding namespace in the any field. */
							JSONObject anyobject = jsonAggregationEvent.getJSONObject("any");
							String namespace = "";
							boolean namespace_flag = false;

							Iterator<String> keyIter_ns = anyobject.keys();
							while (keyIter_ns.hasNext()) {
								String temp = keyIter_ns.next();
								if (temp.substring(0, 1).equals("@")) {
									namespace_flag = true;
									namespace = temp.substring(1, temp.length());
								}
							}

							if (!namespace_flag) {
								Configuration.logger.info("Json Document doesn't have namespace in any field");
								return "Error: Json Document doesn't have namespace in any field"
										+ " for detail validation check for aggregationevent";

							}
							/* finish finding namespace in the any field. */

							/*
							 * Start Validation whether each component use
							 * correct name space
							 */;

							Iterator<String> keyIter = anyobject.keys();
							while (keyIter.hasNext()) {
								String temp = keyIter.next();

								if (!temp.contains(namespace)) {
									Configuration.logger.info("Json Document use invalid namespace in anyfield");
									return "Error: Json Document use invalid namespace in anyfield"
											+ " for detail validation check for aggregationevent";
								}
							}

						}

						if (Configuration.backend.equals("MongoDB")) {
							MongoCaptureUtil m = new MongoCaptureUtil();
							m.captureAggregationEvent(jsonEventList.getJSONObject(i).getJSONObject("AggregationEvent"));
						}
					} else if (jsonEventElement.has("TransformationEvent") == true) {

						/*
						 * startpoint of validation logic for
						 * TransFormationEvent
						 */
						JSONObject transformationEventSchema = schemaLoader.getTransformationEventSchema();
						JSONObject jsonTransformationEvent = jsonEventElement.getJSONObject("TransformationEvent");

						if (!CaptureUtil.validate(jsonTransformationEvent, transformationEventSchema)) {

							Configuration.logger.info(
									"Json Document is not valid" + " detail validation check for TransFormationEvent");
							return "Error: Json Document is not valid"
									+ " for detail validation check for TransFormationEvent";
						}
						/* finish validation logic for TransFormationEvent */

						if (!jsonTransformationEvent.has("recordTime")) {
							jsonTransformationEvent.put("recordTime", System.currentTimeMillis());
						}

						if (jsonTransformationEvent.has("any")) {
							/* start finding namespace in the any field. */
							JSONObject anyobject = jsonTransformationEvent.getJSONObject("any");
							String namespace = "";
							boolean namespace_flag = false;

							Iterator<String> keyIter_ns = anyobject.keys();
							while (keyIter_ns.hasNext()) {
								String temp = keyIter_ns.next();
								if (temp.substring(0, 1).equals("@")) {
									namespace_flag = true;
									namespace = temp.substring(1, temp.length());
								}
							}

							if (!namespace_flag) {
								Configuration.logger.info("Json Document doesn't have namespace in any field");
								return "Error: Json Document doesn't have namespace in any field"
										+ " for detail validation check for TransformationEvent";

							}
							/* finish finding namespace in the any field. */

							/*
							 * Start Validation whether each component use
							 * correct name space
							 */;

							Iterator<String> keyIter = anyobject.keys();
							while (keyIter.hasNext()) {
								String temp = keyIter.next();

								if (!temp.contains(namespace)) {
									Configuration.logger.info("Json Document use invalid namespace in anyfield");
									return "Error: Json Document use invalid namespace in anyfield"
											+ " for detail validation check for TransformationEvent";
								}
							}
						}

						if (Configuration.backend.equals("MongoDB")) {
							MongoCaptureUtil m = new MongoCaptureUtil();
							m.captureTransformationEvent(
									jsonEventList.getJSONObject(i).getJSONObject("TransformationEvent"));
						}
					} else if (jsonEventElement.has("TransactionEvent") == true) {

						/*
						 * startpoint of validation logic for
						 * TransFormationEvent
						 */
						JSONObject transactionEventSchema = schemaLoader.getTransactionEventSchema();
						JSONObject jsonTransactionEvent = jsonEventElement.getJSONObject("TransactionEvent");

						if (!CaptureUtil.validate(jsonTransactionEvent, transactionEventSchema)) {

							Configuration.logger.info(
									"Json Document is not valid." + " detail validation check for TransactionEvent");
							return "Error: Json Document is not valid"
									+ " for detail validation check for TransactionEvent";
						}
						/* finish validation logic for TransFormationEvent */

						if (!jsonTransactionEvent.has("recordTime")) {
							jsonTransactionEvent.put("recordTime", System.currentTimeMillis());
						}

						if (jsonTransactionEvent.has("any")) {
							/* start finding namespace in the any field. */
							JSONObject anyobject = jsonTransactionEvent.getJSONObject("any");
							String namespace = "";
							boolean namespace_flag = false;

							Iterator<String> keyIter_ns = anyobject.keys();
							while (keyIter_ns.hasNext()) {
								String temp = keyIter_ns.next();
								if (temp.substring(0, 1).equals("@")) {
									namespace_flag = true;
									namespace = temp.substring(1, temp.length());
								}
							}

							if (!namespace_flag) {
								Configuration.logger.info("Json Document doesn't have namespace in any field");
								return "Error: Json Document doesn't have namespace in any field"
										+ " for detail validation check for TransactionEvent";

							}
							/* finish finding namespace in the any field. */

							/*
							 * Start Validation whether each component use
							 * correct name space
							 */;

							Iterator<String> keyIter = anyobject.keys();
							while (keyIter.hasNext()) {
								String temp = keyIter.next();

								if (!temp.contains(namespace)) {
									Configuration.logger.info("Json Document use invalid namespace in anyfield");
									return "Error: Json Document use invalid namespace in anyfield"
											+ " for detail validation check for TransactionEvent";
								}
							}

						}

						if (Configuration.backend.equals("MongoDB")) {
							MongoCaptureUtil m = new MongoCaptureUtil();
							m.captureTransactionEvent(jsonEventList.getJSONObject(i).getJSONObject("TransactionEvent"));
						}
					} else {
						Configuration.logger
								.info("Json Document is not valid. " + " It doesn't have standard event_type");
						return "Error: Json Document is not valid" + " It doesn't have standard event_type";
					}

				}
				if (jsonEventList.length() != 0)
					Configuration.logger.info(" EPCIS Document : Captured ");

			} catch (JSONException e) {
				Configuration.logger.info(" Json Document is not valid " + "second_validcheck");
			} catch (Exception e) {
				Configuration.logger.log(Level.ERROR, e.toString());
			}
			return "EPCIS Document : Captured ";

		} else {
			JSONObject jsonEvent = new JSONObject(inputString);
			JSONArray jsonEventList = jsonEvent.getJSONObject("epcis").getJSONObject("EPCISBody")
					.getJSONArray("EventList");

			for (int i = 0; i < jsonEventList.length(); i++) {

				JSONObject jsonEventElement = jsonEventList.getJSONObject(i);

				if (jsonEventElement.has("ObjectEvent") == true) {
					if (Configuration.backend.equals("MongoDB")) {
						MongoCaptureUtil m = new MongoCaptureUtil();
						m.captureObjectEvent(jsonEventElement.getJSONObject("ObjectEvent"));
					}
				} else if (jsonEventElement.has("AggregationEvent") == true) {
					if (Configuration.backend.equals("MongoDB")) {
						MongoCaptureUtil m = new MongoCaptureUtil();
						m.captureAggregationEvent(jsonEventElement.getJSONObject("AggregationEvent"));
					}
				} else if (jsonEventElement.has("TransformationEvent") == true) {
					if (Configuration.backend.equals("MongoDB")) {
						MongoCaptureUtil m = new MongoCaptureUtil();
						m.captureTransformationEvent(jsonEventElement.getJSONObject("TransformationEvent"));
					}
				} else if (jsonEventElement.has("TransactionEvent") == true) {
					if (Configuration.backend.equals("MongoDB")) {
						MongoCaptureUtil m = new MongoCaptureUtil();
						m.captureTransactionEvent(jsonEventElement.getJSONObject("TransactionEvent"));
					}
				}
			}
			return "EPCIS Document : Captured ";
		}
	}
}
