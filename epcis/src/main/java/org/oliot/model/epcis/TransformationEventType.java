//
// 이 파일은 JAXB(JavaTM Architecture for XML Binding) 참조 구현 2.2.8-b130911.1802 버전을 통해 생성되었습니다. 
// <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>를 참조하십시오. 
// 이 파일을 수정하면 소스 스키마를 재컴파일할 때 수정 사항이 손실됩니다. 
// 생성 날짜: 2016.04.29 시간 09:20:38 AM KST 
//

package org.oliot.model.epcis;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.w3c.dom.Element;

/**
 * 
 * Transformation Event captures an event in which inputs are consumed and
 * outputs are produced
 * 
 * 
 * <p>
 * TransformationEventType complex type에 대한 Java 클래스입니다.
 * 
 * <p>
 * 다음 스키마 단편이 이 클래스에 포함되는 필요한 콘텐츠를 지정합니다.
 * 
 * <pre>
 * &lt;complexType name="TransformationEventType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:epcglobal:epcis:xsd:1}EPCISEventType">
 *       &lt;sequence>
 *         &lt;element name="inputEPCList" type="{urn:epcglobal:epcis:xsd:1}EPCListType" minOccurs="0"/>
 *         &lt;element name="inputQuantityList" type="{urn:epcglobal:epcis:xsd:1}QuantityListType" minOccurs="0"/>
 *         &lt;element name="outputEPCList" type="{urn:epcglobal:epcis:xsd:1}EPCListType" minOccurs="0"/>
 *         &lt;element name="outputQuantityList" type="{urn:epcglobal:epcis:xsd:1}QuantityListType" minOccurs="0"/>
 *         &lt;element name="transformationID" type="{urn:epcglobal:epcis:xsd:1}TransformationIDType" minOccurs="0"/>
 *         &lt;element name="bizStep" type="{urn:epcglobal:epcis:xsd:1}BusinessStepIDType" minOccurs="0"/>
 *         &lt;element name="disposition" type="{urn:epcglobal:epcis:xsd:1}DispositionIDType" minOccurs="0"/>
 *         &lt;element name="readPoint" type="{urn:epcglobal:epcis:xsd:1}ReadPointType" minOccurs="0"/>
 *         &lt;element name="bizLocation" type="{urn:epcglobal:epcis:xsd:1}BusinessLocationType" minOccurs="0"/>
 *         &lt;element name="bizTransactionList" type="{urn:epcglobal:epcis:xsd:1}BusinessTransactionListType" minOccurs="0"/>
 *         &lt;element name="sourceList" type="{urn:epcglobal:epcis:xsd:1}SourceListType" minOccurs="0"/>
 *         &lt;element name="destinationList" type="{urn:epcglobal:epcis:xsd:1}DestinationListType" minOccurs="0"/>
 *         &lt;element name="ilmd" type="{urn:epcglobal:epcis:xsd:1}ILMDType" minOccurs="0"/>
 *         &lt;element name="extension" type="{urn:epcglobal:epcis:xsd:1}TransformationEventExtensionType" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;anyAttribute processContents='lax'/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransformationEventType", propOrder = { "inputEPCList", "inputQuantityList", "outputEPCList",
		"outputQuantityList", "transformationID", "bizStep", "disposition", "readPoint", "bizLocation",
		"bizTransactionList", "sourceList", "destinationList", "ilmd", "extension", "any" })
public class TransformationEventType extends EPCISEventType {

	public void setAny(List<Object> any) {
		this.any = any;
	}

	protected EPCListType inputEPCList;
	protected QuantityListType inputQuantityList;
	protected EPCListType outputEPCList;
	protected QuantityListType outputQuantityList;
	@XmlSchemaType(name = "anyURI")
	protected String transformationID;
	@XmlSchemaType(name = "anyURI")
	protected String bizStep;
	@XmlSchemaType(name = "anyURI")
	protected String disposition;
	protected ReadPointType readPoint;
	protected BusinessLocationType bizLocation;
	protected BusinessTransactionListType bizTransactionList;
	protected SourceListType sourceList;
	protected DestinationListType destinationList;
	protected ILMDType ilmd;
	protected TransformationEventExtensionType extension;
	@XmlAnyElement(lax = true)
	protected List<Object> any;

	/**
	 * inputEPCList 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link EPCListType }
	 * 
	 */
	public EPCListType getInputEPCList() {
		return inputEPCList;
	}

	/**
	 * inputEPCList 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link EPCListType }
	 * 
	 */
	public void setInputEPCList(EPCListType value) {
		this.inputEPCList = value;
	}

	/**
	 * inputQuantityList 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link QuantityListType }
	 * 
	 */
	public QuantityListType getInputQuantityList() {
		return inputQuantityList;
	}

	/**
	 * inputQuantityList 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link QuantityListType }
	 * 
	 */
	public void setInputQuantityList(QuantityListType value) {
		this.inputQuantityList = value;
	}

	/**
	 * outputEPCList 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link EPCListType }
	 * 
	 */
	public EPCListType getOutputEPCList() {
		return outputEPCList;
	}

	/**
	 * outputEPCList 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link EPCListType }
	 * 
	 */
	public void setOutputEPCList(EPCListType value) {
		this.outputEPCList = value;
	}

	/**
	 * outputQuantityList 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link QuantityListType }
	 * 
	 */
	public QuantityListType getOutputQuantityList() {
		return outputQuantityList;
	}

	/**
	 * outputQuantityList 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link QuantityListType }
	 * 
	 */
	public void setOutputQuantityList(QuantityListType value) {
		this.outputQuantityList = value;
	}

	/**
	 * transformationID 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getTransformationID() {
		return transformationID;
	}

	/**
	 * transformationID 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setTransformationID(String value) {
		this.transformationID = value;
	}

	/**
	 * bizStep 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getBizStep() {
		return bizStep;
	}

	/**
	 * bizStep 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setBizStep(String value) {
		this.bizStep = value;
	}

	/**
	 * disposition 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDisposition() {
		return disposition;
	}

	/**
	 * disposition 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDisposition(String value) {
		this.disposition = value;
	}

	/**
	 * readPoint 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link ReadPointType }
	 * 
	 */
	public ReadPointType getReadPoint() {
		return readPoint;
	}

	/**
	 * readPoint 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link ReadPointType }
	 * 
	 */
	public void setReadPoint(ReadPointType value) {
		this.readPoint = value;
	}

	/**
	 * bizLocation 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link BusinessLocationType }
	 * 
	 */
	public BusinessLocationType getBizLocation() {
		return bizLocation;
	}

	/**
	 * bizLocation 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link BusinessLocationType }
	 * 
	 */
	public void setBizLocation(BusinessLocationType value) {
		this.bizLocation = value;
	}

	/**
	 * bizTransactionList 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link BusinessTransactionListType }
	 * 
	 */
	public BusinessTransactionListType getBizTransactionList() {
		return bizTransactionList;
	}

	/**
	 * bizTransactionList 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link BusinessTransactionListType }
	 * 
	 */
	public void setBizTransactionList(BusinessTransactionListType value) {
		this.bizTransactionList = value;
	}

	/**
	 * sourceList 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link SourceListType }
	 * 
	 */
	public SourceListType getSourceList() {
		return sourceList;
	}

	/**
	 * sourceList 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link SourceListType }
	 * 
	 */
	public void setSourceList(SourceListType value) {
		this.sourceList = value;
	}

	/**
	 * destinationList 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link DestinationListType }
	 * 
	 */
	public DestinationListType getDestinationList() {
		return destinationList;
	}

	/**
	 * destinationList 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link DestinationListType }
	 * 
	 */
	public void setDestinationList(DestinationListType value) {
		this.destinationList = value;
	}

	/**
	 * ilmd 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link ILMDType }
	 * 
	 */
	public ILMDType getIlmd() {
		return ilmd;
	}

	/**
	 * ilmd 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link ILMDType }
	 * 
	 */
	public void setIlmd(ILMDType value) {
		this.ilmd = value;
	}

	/**
	 * extension 속성의 값을 가져옵니다.
	 * 
	 * @return possible object is {@link TransformationEventExtensionType }
	 * 
	 */
	public TransformationEventExtensionType getExtension() {
		return extension;
	}

	/**
	 * extension 속성의 값을 설정합니다.
	 * 
	 * @param value
	 *            allowed object is {@link TransformationEventExtensionType }
	 * 
	 */
	public void setExtension(TransformationEventExtensionType value) {
		this.extension = value;
	}

	/**
	 * Gets the value of the any property.
	 * 
	 * <p>
	 * This accessor method returns a reference to the live list, not a
	 * snapshot. Therefore any modification you make to the returned list will
	 * be present inside the JAXB object. This is why there is not a
	 * <CODE>set</CODE> method for the any property.
	 * 
	 * <p>
	 * For example, to add a new item, do as follows:
	 * 
	 * <pre>
	 * getAny().add(newItem);
	 * </pre>
	 * 
	 * 
	 * <p>
	 * Objects of the following type(s) are allowed in the list {@link Object }
	 * {@link Element }
	 * 
	 * 
	 */
	public List<Object> getAny() {
		if (any == null) {
			any = new ArrayList<Object>();
		}
		return this.any;
	}

}
