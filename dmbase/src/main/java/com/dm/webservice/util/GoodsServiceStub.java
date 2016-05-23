/**
 * GoodsServiceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.1  Built on : Feb 20, 2016 (10:01:29 GMT)
 */
package com.dm.webservice.util;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


/*
 *  GoodsServiceStub java implementation
 */
public class GoodsServiceStub extends org.apache.axis2.client.Stub {
    private static int counter = 0;
    protected org.apache.axis2.description.AxisOperation[] _operations;

    //hashmaps to keep the fault mapping
    private java.util.HashMap faultExceptionNameMap = new java.util.HashMap();
    private java.util.HashMap faultExceptionClassNameMap = new java.util.HashMap();
    private java.util.HashMap faultMessageMap = new java.util.HashMap();
    private javax.xml.namespace.QName[] opNameArray = null;

    /**
     *Constructor that takes in a configContext
     */
    public GoodsServiceStub(
        org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint) throws org.apache.axis2.AxisFault {
        this(configurationContext, targetEndpoint, false);
    }

    /**
     * Constructor that takes in a configContext  and useseperate listner
     */
    public GoodsServiceStub(
        org.apache.axis2.context.ConfigurationContext configurationContext,
        java.lang.String targetEndpoint, boolean useSeparateListener)
        throws org.apache.axis2.AxisFault {
        //To populate AxisService
        populateAxisService();
        populateFaults();

        _serviceClient = new org.apache.axis2.client.ServiceClient(configurationContext,
                _service);

        _serviceClient.getOptions()
                      .setTo(new org.apache.axis2.addressing.EndpointReference(
                targetEndpoint));
        _serviceClient.getOptions().setUseSeparateListener(useSeparateListener);

        //Set the soap version
        _serviceClient.getOptions()
                      .setSoapVersionURI(org.apache.axiom.soap.SOAP12Constants.SOAP_ENVELOPE_NAMESPACE_URI);
    }

    /**
     * Default Constructor
     */
    public GoodsServiceStub(
        org.apache.axis2.context.ConfigurationContext configurationContext)
        throws org.apache.axis2.AxisFault {
        this(configurationContext,
            "http://localhost/ecommerce/services/goodsService.goodsServiceHttpSoap12Endpoint/");
    }

    /**
     * Default Constructor
     */
    public GoodsServiceStub() throws org.apache.axis2.AxisFault {
        this(
            "http://localhost/ecommerce/services/goodsService.goodsServiceHttpSoap12Endpoint/");
    }

    /**
     * Constructor taking the target endpoint
     */
    public GoodsServiceStub(java.lang.String targetEndpoint)
        throws org.apache.axis2.AxisFault {
        this(null, targetEndpoint);
    }

    private static synchronized java.lang.String getUniqueSuffix() {
        // reset the counter if it is greater than 99999
        if (counter > 99999) {
            counter = 0;
        }

        counter = counter + 1;

        return java.lang.Long.toString(java.lang.System.currentTimeMillis()) +
        "_" + counter;
    }

    private void populateAxisService() throws org.apache.axis2.AxisFault {
        //creating the Service with a unique name
        _service = new org.apache.axis2.description.AxisService("GoodsService" +
                getUniqueSuffix());
        addAnonymousOperations();

        //creating the operations
        org.apache.axis2.description.AxisOperation __operation;

        _operations = new org.apache.axis2.description.AxisOperation[1];

        __operation = new org.apache.axis2.description.OutInAxisOperation();

        __operation.setName(new javax.xml.namespace.QName(
                "http://service.webservice.app.com", "getGoodsList"));
        _service.addOperation(__operation);

        _operations[0] = __operation;
    }

    //populates the faults
    private void populateFaults() {
    }

    /**
     * Auto generated method signature
     *
     * @see com.dm.webservice.util.GoodsService#getGoodsList
     * @param getGoodsList
     */
    public com.dm.webservice.util.GoodsServiceStub.GetGoodsListResponse getGoodsList(
        com.dm.webservice.util.GoodsServiceStub.GetGoodsList getGoodsList)
        throws java.rmi.RemoteException {
        org.apache.axis2.context.MessageContext _messageContext = null;

        try {
            org.apache.axis2.client.OperationClient _operationClient = _serviceClient.createClient(_operations[0].getName());
            _operationClient.getOptions().setAction("urn:getGoodsList");
            _operationClient.getOptions().setExceptionToBeThrownOnSOAPFault(true);

            addPropertyToOperationClient(_operationClient,
                org.apache.axis2.description.WSDL2Constants.ATTR_WHTTP_QUERY_PARAMETER_SEPARATOR,
                "&");

            // create a message context
            _messageContext = new org.apache.axis2.context.MessageContext();

            // create SOAP envelope with that payload
            org.apache.axiom.soap.SOAPEnvelope env = null;

            env = toEnvelope(getFactory(_operationClient.getOptions()
                                                        .getSoapVersionURI()),
                    getGoodsList,
                    optimizeContent(
                        new javax.xml.namespace.QName(
                            "http://service.webservice.app.com", "getGoodsList")),
                    new javax.xml.namespace.QName(
                        "http://service.webservice.app.com", "getGoodsList"));

            //adding SOAP soap_headers
            _serviceClient.addHeadersToEnvelope(env);
            // set the message context with that soap envelope
            _messageContext.setEnvelope(env);

            // add the message contxt to the operation client
            _operationClient.addMessageContext(_messageContext);

            //execute the operation client
            _operationClient.execute(true);

            org.apache.axis2.context.MessageContext _returnMessageContext = _operationClient.getMessageContext(org.apache.axis2.wsdl.WSDLConstants.MESSAGE_LABEL_IN_VALUE);
            org.apache.axiom.soap.SOAPEnvelope _returnEnv = _returnMessageContext.getEnvelope();

            java.lang.Object object = fromOM(_returnEnv.getBody()
                                                       .getFirstElement(),
                    com.dm.webservice.util.GoodsServiceStub.GetGoodsListResponse.class);

            return (com.dm.webservice.util.GoodsServiceStub.GetGoodsListResponse) object;
        } catch (org.apache.axis2.AxisFault f) {
            org.apache.axiom.om.OMElement faultElt = f.getDetail();

            if (faultElt != null) {
                if (faultExceptionNameMap.containsKey(
                            new org.apache.axis2.client.FaultMapKey(
                                faultElt.getQName(), "getGoodsList"))) {
                    //make the fault by reflection
                    try {
                        java.lang.String exceptionClassName = (java.lang.String) faultExceptionClassNameMap.get(new org.apache.axis2.client.FaultMapKey(
                                    faultElt.getQName(), "getGoodsList"));
                        java.lang.Class exceptionClass = java.lang.Class.forName(exceptionClassName);
                        java.lang.reflect.Constructor constructor = exceptionClass.getConstructor(java.lang.String.class);
                        java.lang.Exception ex = (java.lang.Exception) constructor.newInstance(f.getMessage());

                        //message class
                        java.lang.String messageClassName = (java.lang.String) faultMessageMap.get(new org.apache.axis2.client.FaultMapKey(
                                    faultElt.getQName(), "getGoodsList"));
                        java.lang.Class messageClass = java.lang.Class.forName(messageClassName);
                        java.lang.Object messageObject = fromOM(faultElt,
                                messageClass);
                        java.lang.reflect.Method m = exceptionClass.getMethod("setFaultMessage",
                                new java.lang.Class[] { messageClass });
                        m.invoke(ex, new java.lang.Object[] { messageObject });

                        throw new java.rmi.RemoteException(ex.getMessage(), ex);
                    } catch (java.lang.ClassCastException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.ClassNotFoundException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.NoSuchMethodException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.reflect.InvocationTargetException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.IllegalAccessException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    } catch (java.lang.InstantiationException e) {
                        // we cannot intantiate the class - throw the original Axis fault
                        throw f;
                    }
                } else {
                    throw f;
                }
            } else {
                throw f;
            }
        } finally {
            if (_messageContext.getTransportOut() != null) {
                _messageContext.getTransportOut().getSender()
                               .cleanup(_messageContext);
            }
        }
    }

    private boolean optimizeContent(javax.xml.namespace.QName opName) {
        if (opNameArray == null) {
            return false;
        }

        for (int i = 0; i < opNameArray.length; i++) {
            if (opName.equals(opNameArray[i])) {
                return true;
            }
        }

        return false;
    }

    private org.apache.axiom.om.OMElement toOM(
        com.dm.webservice.util.GoodsServiceStub.GetGoodsList param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.dm.webservice.util.GoodsServiceStub.GetGoodsList.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        com.dm.webservice.util.GoodsServiceStub.GetGoodsListResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(com.dm.webservice.util.GoodsServiceStub.GetGoodsListResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        com.dm.webservice.util.GoodsServiceStub.GetGoodsList param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();
            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    com.dm.webservice.util.GoodsServiceStub.GetGoodsList.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    /* methods to provide back word compatibility */

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type) throws org.apache.axis2.AxisFault {
        try {
            if (com.dm.webservice.util.GoodsServiceStub.GetGoodsList.class.equals(
                        type)) {
                return com.dm.webservice.util.GoodsServiceStub.GetGoodsList.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (com.dm.webservice.util.GoodsServiceStub.GetGoodsListResponse.class.equals(
                        type)) {
                return com.dm.webservice.util.GoodsServiceStub.GetGoodsListResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    //http://localhost/ecommerce/services/goodsService.goodsServiceHttpSoap12Endpoint/
    public static class ExtensionMapper {
        public static java.lang.Object getTypeObject(
            java.lang.String namespaceURI, java.lang.String typeName,
            javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
            if ("http://model.eGoods.topie.com/xsd".equals(namespaceURI) &&
                    "EGoods".equals(typeName)) {
                return EGoods.Factory.parse(reader);
            }

            throw new org.apache.axis2.databinding.ADBException(
                "Unsupported type " + namespaceURI + " " + typeName);
        }
    }

    public static class GetGoodsListResponse implements org.apache.axis2.databinding.ADBBean {
        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://service.webservice.app.com",
                "getGoodsListResponse", "ns2");

        /**
         * field for _return
         * This was an Array!
         */
        protected EGoods[] local_return;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean local_returnTracker = false;

        public boolean is_returnSpecified() {
            return local_returnTracker;
        }

        /**
         * Auto generated getter method
         * @return EGoods[]
         */
        public EGoods[] get_return() {
            return local_return;
        }

        /**
         * validate the array for _return
         */
        protected void validate_return(EGoods[] param) {
        }

        /**
         * Auto generated setter method
         * @param param _return
         */
        public void set_return(EGoods[] param) {
            validate_return(param);

            local_returnTracker = true;

            this.local_return = param;
        }

        /**
         * Auto generated add method for the array for convenience
         * @param param EGoods
         */
        public void add_return(EGoods param) {
            if (local_return == null) {
                local_return = new EGoods[] {  };
            }

            //update the setting tracker
            local_returnTracker = true;

            java.util.List list = org.apache.axis2.databinding.utils.ConverterUtil.toList(local_return);
            list.add(param);
            this.local_return = (EGoods[]) list.toArray(new EGoods[list.size()]);
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            return factory.createOMElement(new org.apache.axis2.databinding.ADBDataSource(
                    this, MY_QNAME));
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            serialize(parentQName, xmlWriter, false);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            javax.xml.stream.XMLStreamWriter xmlWriter, boolean serializeType)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();
            writeStartElement(prefix, namespace, parentQName.getLocalPart(),
                xmlWriter);

            if (serializeType) {
                java.lang.String namespacePrefix = registerPrefix(xmlWriter,
                        "http://service.webservice.app.com");

                if ((namespacePrefix != null) &&
                        (namespacePrefix.trim().length() > 0)) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "type",
                        namespacePrefix + ":getGoodsListResponse", xmlWriter);
                } else {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "type",
                        "getGoodsListResponse", xmlWriter);
                }
            }

            if (local_returnTracker) {
                if (local_return != null) {
                    for (int i = 0; i < local_return.length; i++) {
                        if (local_return[i] != null) {
                            local_return[i].serialize(new javax.xml.namespace.QName(
                                    "http://service.webservice.app.com",
                                    "return"), xmlWriter);
                        } else {
                            writeStartElement(null,
                                "http://service.webservice.app.com", "return",
                                xmlWriter);

                            // write the nil attribute
                            writeAttribute("xsi",
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "nil", "1", xmlWriter);
                            xmlWriter.writeEndElement();
                        }
                    }
                } else {
                    writeStartElement(null,
                        "http://service.webservice.app.com", "return", xmlWriter);

                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                    xmlWriter.writeEndElement();
                }
            }

            xmlWriter.writeEndElement();
        }

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://service.webservice.app.com")) {
                return "ns2";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix,
            java.lang.String namespace, java.lang.String localPart,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

            if (writerPrefix != null) {
                xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

            if (writerPrefix != null) {
                xmlWriter.writeAttribute(writerPrefix, namespace, attName,
                    attValue);
            } else {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
                xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                xmlWriter.writeAttribute(registerPrefix(xmlWriter, namespace),
                    namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(attributePrefix, namespace, attName,
                    attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();

                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);

                    if ((uri == null) || (uri.length() == 0)) {
                        break;
                    }

                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Factory.class);

            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static GetGoodsListResponse parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                GetGoodsListResponse object = new GetGoodsListResponse();

                int event;
                javax.xml.namespace.QName currentQName = null;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    currentQName = reader.getName();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"getGoodsListResponse".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (GetGoodsListResponse) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    java.util.ArrayList list1 = new java.util.ArrayList();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://service.webservice.app.com", "return").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "return").equals(
                                reader.getName())) {
                        // Process the array and step past its final element's end.
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            list1.add(null);
                            reader.next();
                        } else {
                            list1.add(EGoods.Factory.parse(reader));
                        }

                        //loop until we find a start element that is not part of this array
                        boolean loopDone1 = false;

                        while (!loopDone1) {
                            // We should be at the end element, but make sure
                            while (!reader.isEndElement())
                                reader.next();

                            // Step out of this element
                            reader.next();

                            // Step to next element event.
                            while (!reader.isStartElement() &&
                                    !reader.isEndElement())
                                reader.next();

                            if (reader.isEndElement()) {
                                //two continuous end elements means we are exiting the xml structure
                                loopDone1 = true;
                            } else {
                                if (new javax.xml.namespace.QName(
                                            "http://service.webservice.app.com",
                                            "return").equals(reader.getName())) {
                                    nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                            "nil");

                                    if ("true".equals(nillableValue) ||
                                            "1".equals(nillableValue)) {
                                        list1.add(null);
                                        reader.next();
                                    } else {
                                        list1.add(EGoods.Factory.parse(reader));
                                    }
                                } else {
                                    loopDone1 = true;
                                }
                            }
                        }

                        // call the converter utility  to convert and set the array
                        object.set_return((EGoods[]) org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                EGoods.class, list1));
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // 2 - A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class

		@Override
		public XMLStreamReader getPullParser(QName arg0)
				throws XMLStreamException {
			// TODO Auto-generated method stub
			return null;
		}
    }

    public static class GetGoodsList implements org.apache.axis2.databinding.ADBBean {
        public static final javax.xml.namespace.QName MY_QNAME = new javax.xml.namespace.QName("http://service.webservice.app.com",
                "getGoodsList", "ns2");

        /**
         * field for PageNum
         */
        protected int localPageNum;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localPageNumTracker = false;

        /**
         * field for PageSize
         */
        protected int localPageSize;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localPageSizeTracker = false;

        /**
         * field for Type
         */
        protected java.lang.String localType;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localTypeTracker = false;

        /**
         * field for Sort
         */
        protected java.lang.String localSort;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localSortTracker = false;

        public boolean isPageNumSpecified() {
            return localPageNumTracker;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getPageNum() {
            return localPageNum;
        }

        /**
         * Auto generated setter method
         * @param param PageNum
         */
        public void setPageNum(int param) {
            // setting primitive attribute tracker to true
            localPageNumTracker = param != java.lang.Integer.MIN_VALUE;

            this.localPageNum = param;
        }

        public boolean isPageSizeSpecified() {
            return localPageSizeTracker;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getPageSize() {
            return localPageSize;
        }

        /**
         * Auto generated setter method
         * @param param PageSize
         */
        public void setPageSize(int param) {
            // setting primitive attribute tracker to true
            localPageSizeTracker = param != java.lang.Integer.MIN_VALUE;

            this.localPageSize = param;
        }

        public boolean isTypeSpecified() {
            return localTypeTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getType() {
            return localType;
        }

        /**
         * Auto generated setter method
         * @param param Type
         */
        public void setType(java.lang.String param) {
            localTypeTracker = true;

            this.localType = param;
        }

        public boolean isSortSpecified() {
            return localSortTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getSort() {
            return localSort;
        }

        /**
         * Auto generated setter method
         * @param param Sort
         */
        public void setSort(java.lang.String param) {
            localSortTracker = true;

            this.localSort = param;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            return factory.createOMElement(new org.apache.axis2.databinding.ADBDataSource(
                    this, MY_QNAME));
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            serialize(parentQName, xmlWriter, false);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            javax.xml.stream.XMLStreamWriter xmlWriter, boolean serializeType)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();
            writeStartElement(prefix, namespace, parentQName.getLocalPart(),
                xmlWriter);

            if (serializeType) {
                java.lang.String namespacePrefix = registerPrefix(xmlWriter,
                        "http://service.webservice.app.com");

                if ((namespacePrefix != null) &&
                        (namespacePrefix.trim().length() > 0)) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "type",
                        namespacePrefix + ":getGoodsList", xmlWriter);
                } else {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "type",
                        "getGoodsList", xmlWriter);
                }
            }

            if (localPageNumTracker) {
                namespace = "http://service.webservice.app.com";
                writeStartElement(null, namespace, "pageNum", xmlWriter);

                if (localPageNum == java.lang.Integer.MIN_VALUE) {
                    throw new org.apache.axis2.databinding.ADBException(
                        "pageNum cannot be null!!");
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localPageNum));
                }

                xmlWriter.writeEndElement();
            }

            if (localPageSizeTracker) {
                namespace = "http://service.webservice.app.com";
                writeStartElement(null, namespace, "pageSize", xmlWriter);

                if (localPageSize == java.lang.Integer.MIN_VALUE) {
                    throw new org.apache.axis2.databinding.ADBException(
                        "pageSize cannot be null!!");
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localPageSize));
                }

                xmlWriter.writeEndElement();
            }

            if (localTypeTracker) {
                namespace = "http://service.webservice.app.com";
                writeStartElement(null, namespace, "type", xmlWriter);

                if (localType == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localType);
                }

                xmlWriter.writeEndElement();
            }

            if (localSortTracker) {
                namespace = "http://service.webservice.app.com";
                writeStartElement(null, namespace, "sort", xmlWriter);

                if (localSort == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localSort);
                }

                xmlWriter.writeEndElement();
            }

            xmlWriter.writeEndElement();
        }

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://service.webservice.app.com")) {
                return "ns2";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix,
            java.lang.String namespace, java.lang.String localPart,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

            if (writerPrefix != null) {
                xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

            if (writerPrefix != null) {
                xmlWriter.writeAttribute(writerPrefix, namespace, attName,
                    attValue);
            } else {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
                xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                xmlWriter.writeAttribute(registerPrefix(xmlWriter, namespace),
                    namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(attributePrefix, namespace, attName,
                    attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();

                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);

                    if ((uri == null) || (uri.length() == 0)) {
                        break;
                    }

                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Factory.class);

            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static GetGoodsList parse(
                javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                GetGoodsList object = new GetGoodsList();

                int event;
                javax.xml.namespace.QName currentQName = null;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    currentQName = reader.getName();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"getGoodsList".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (GetGoodsList) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://service.webservice.app.com", "pageNum").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "pageNum").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            throw new org.apache.axis2.databinding.ADBException(
                                "The element: " + "pageNum" +
                                "  cannot be null");
                        }

                        java.lang.String content = reader.getElementText();

                        object.setPageNum(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setPageNum(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://service.webservice.app.com", "pageSize").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "pageSize").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if ("true".equals(nillableValue) ||
                                "1".equals(nillableValue)) {
                            throw new org.apache.axis2.databinding.ADBException(
                                "The element: " + "pageSize" +
                                "  cannot be null");
                        }

                        java.lang.String content = reader.getElementText();

                        object.setPageSize(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                content));

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setPageSize(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://service.webservice.app.com", "type").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "type").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://service.webservice.app.com", "sort").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "sort").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setSort(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // 2 - A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class

		@Override
		public XMLStreamReader getPullParser(QName arg0)
				throws XMLStreamException {
			// TODO Auto-generated method stub
			return null;
		}
    }

    public static class EGoods implements org.apache.axis2.databinding.ADBBean {
        /* This type was generated from the piece of schema that had
           name = EGoods
           Namespace URI = http://model.eGoods.topie.com/xsd
           Namespace Prefix = ns1
         */

        /**
         * field for AfterService
         */
        protected java.lang.String localAfterService;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localAfterServiceTracker = false;

        /**
         * field for BuyPrice
         */
        protected java.math.BigDecimal localBuyPrice;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localBuyPriceTracker = false;

        /**
         * field for DeleteFlag
         */
        protected java.lang.String localDeleteFlag;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localDeleteFlagTracker = false;

        /**
         * field for Detail
         */
        protected java.lang.String localDetail;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localDetailTracker = false;

        /**
         * field for DynamicState
         */
        protected java.lang.String localDynamicState;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localDynamicStateTracker = false;

        /**
         * field for GoodsId
         */
        protected int localGoodsId;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localGoodsIdTracker = false;

        /**
         * field for GoodsNo
         */
        protected int localGoodsNo;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localGoodsNoTracker = false;

        /**
         * field for Id
         */
        protected int localId;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localIdTracker = false;

        /**
         * field for Images
         */
        protected java.lang.String localImages;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localImagesTracker = false;

        /**
         * field for Info
         */
        protected java.lang.String localInfo;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localInfoTracker = false;

        /**
         * field for Integral
         */
        protected java.math.BigDecimal localIntegral;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localIntegralTracker = false;

        /**
         * field for IsPresell
         */
        protected boolean localIsPresell;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localIsPresellTracker = false;

        /**
         * field for IsSale
         */
        protected boolean localIsSale;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localIsSaleTracker = false;

        /**
         * field for Keywords
         */
        protected java.lang.String localKeywords;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localKeywordsTracker = false;

        /**
         * field for MarketPrice
         */
        protected java.math.BigDecimal localMarketPrice;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localMarketPriceTracker = false;

        /**
         * field for Name
         */
        protected java.lang.String localName;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localNameTracker = false;

        /**
         * field for OtherFee
         */
        protected java.math.BigDecimal localOtherFee;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localOtherFeeTracker = false;

        /**
         * field for PublishTime
         */
        protected java.util.Date localPublishTime;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localPublishTimeTracker = false;

        /**
         * field for RelatedGoods
         */
        protected java.lang.String localRelatedGoods;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localRelatedGoodsTracker = false;

        /**
         * field for Remark
         */
        protected java.lang.String localRemark;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localRemarkTracker = false;

        /**
         * field for SaleNum
         */
        protected int localSaleNum;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localSaleNumTracker = false;

        /**
         * field for SalePrice
         */
        protected java.math.BigDecimal localSalePrice;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localSalePriceTracker = false;

        /**
         * field for SaleTime
         */
        protected java.util.Date localSaleTime;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localSaleTimeTracker = false;

        /**
         * field for ShowImage
         */
        protected java.lang.String localShowImage;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localShowImageTracker = false;

        /**
         * field for SoldOutTime
         */
        protected java.util.Date localSoldOutTime;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localSoldOutTimeTracker = false;

        /**
         * field for Stock
         */
        protected java.math.BigDecimal localStock;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localStockTracker = false;

        /**
         * field for StockRulesId
         */
        protected int localStockRulesId;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localStockRulesIdTracker = false;

        /**
         * field for StockRulesName
         */
        protected java.lang.String localStockRulesName;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localStockRulesNameTracker = false;

        /**
         * field for TemplateId
         */
        protected int localTemplateId;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localTemplateIdTracker = false;

        /**
         * field for Type
         */
        protected java.lang.String localType;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localTypeTracker = false;

        /**
         * field for TypeName
         */
        protected java.lang.String localTypeName;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localTypeNameTracker = false;

        /**
         * field for Uom
         */
        protected java.lang.String localUom;

        /*  This tracker boolean wil be used to detect whether the user called the set method
         *   for this attribute. It will be used to determine whether to include this field
         *   in the serialized XML
         */
        protected boolean localUomTracker = false;

        public boolean isAfterServiceSpecified() {
            return localAfterServiceTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getAfterService() {
            return localAfterService;
        }

        /**
         * Auto generated setter method
         * @param param AfterService
         */
        public void setAfterService(java.lang.String param) {
            localAfterServiceTracker = true;

            this.localAfterService = param;
        }

        public boolean isBuyPriceSpecified() {
            return localBuyPriceTracker;
        }

        /**
         * Auto generated getter method
         * @return java.math.BigDecimal
         */
        public java.math.BigDecimal getBuyPrice() {
            return localBuyPrice;
        }

        /**
         * Auto generated setter method
         * @param param BuyPrice
         */
        public void setBuyPrice(java.math.BigDecimal param) {
            localBuyPriceTracker = true;

            this.localBuyPrice = param;
        }

        public boolean isDeleteFlagSpecified() {
            return localDeleteFlagTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getDeleteFlag() {
            return localDeleteFlag;
        }

        /**
         * Auto generated setter method
         * @param param DeleteFlag
         */
        public void setDeleteFlag(java.lang.String param) {
            localDeleteFlagTracker = true;

            this.localDeleteFlag = param;
        }

        public boolean isDetailSpecified() {
            return localDetailTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getDetail() {
            return localDetail;
        }

        /**
         * Auto generated setter method
         * @param param Detail
         */
        public void setDetail(java.lang.String param) {
            localDetailTracker = true;

            this.localDetail = param;
        }

        public boolean isDynamicStateSpecified() {
            return localDynamicStateTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getDynamicState() {
            return localDynamicState;
        }

        /**
         * Auto generated setter method
         * @param param DynamicState
         */
        public void setDynamicState(java.lang.String param) {
            localDynamicStateTracker = true;

            this.localDynamicState = param;
        }

        public boolean isGoodsIdSpecified() {
            return localGoodsIdTracker;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getGoodsId() {
            return localGoodsId;
        }

        /**
         * Auto generated setter method
         * @param param GoodsId
         */
        public void setGoodsId(int param) {
            localGoodsIdTracker = true;

            this.localGoodsId = param;
        }

        public boolean isGoodsNoSpecified() {
            return localGoodsNoTracker;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getGoodsNo() {
            return localGoodsNo;
        }

        /**
         * Auto generated setter method
         * @param param GoodsNo
         */
        public void setGoodsNo(int param) {
            localGoodsNoTracker = true;

            this.localGoodsNo = param;
        }

        public boolean isIdSpecified() {
            return localIdTracker;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getId() {
            return localId;
        }

        /**
         * Auto generated setter method
         * @param param Id
         */
        public void setId(int param) {
            localIdTracker = true;

            this.localId = param;
        }

        public boolean isImagesSpecified() {
            return localImagesTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getImages() {
            return localImages;
        }

        /**
         * Auto generated setter method
         * @param param Images
         */
        public void setImages(java.lang.String param) {
            localImagesTracker = true;

            this.localImages = param;
        }

        public boolean isInfoSpecified() {
            return localInfoTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getInfo() {
            return localInfo;
        }

        /**
         * Auto generated setter method
         * @param param Info
         */
        public void setInfo(java.lang.String param) {
            localInfoTracker = true;

            this.localInfo = param;
        }

        public boolean isIntegralSpecified() {
            return localIntegralTracker;
        }

        /**
         * Auto generated getter method
         * @return java.math.BigDecimal
         */
        public java.math.BigDecimal getIntegral() {
            return localIntegral;
        }

        /**
         * Auto generated setter method
         * @param param Integral
         */
        public void setIntegral(java.math.BigDecimal param) {
            localIntegralTracker = true;

            this.localIntegral = param;
        }

        public boolean isIsPresellSpecified() {
            return localIsPresellTracker;
        }

        /**
         * Auto generated getter method
         * @return boolean
         */
        public boolean getIsPresell() {
            return localIsPresell;
        }

        /**
         * Auto generated setter method
         * @param param IsPresell
         */
        public void setIsPresell(boolean param) {
            localIsPresellTracker = true;

            this.localIsPresell = param;
        }

        public boolean isIsSaleSpecified() {
            return localIsSaleTracker;
        }

        /**
         * Auto generated getter method
         * @return boolean
         */
        public boolean getIsSale() {
            return localIsSale;
        }

        /**
         * Auto generated setter method
         * @param param IsSale
         */
        public void setIsSale(boolean param) {
            localIsSaleTracker = true;

            this.localIsSale = param;
        }

        public boolean isKeywordsSpecified() {
            return localKeywordsTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getKeywords() {
            return localKeywords;
        }

        /**
         * Auto generated setter method
         * @param param Keywords
         */
        public void setKeywords(java.lang.String param) {
            localKeywordsTracker = true;

            this.localKeywords = param;
        }

        public boolean isMarketPriceSpecified() {
            return localMarketPriceTracker;
        }

        /**
         * Auto generated getter method
         * @return java.math.BigDecimal
         */
        public java.math.BigDecimal getMarketPrice() {
            return localMarketPrice;
        }

        /**
         * Auto generated setter method
         * @param param MarketPrice
         */
        public void setMarketPrice(java.math.BigDecimal param) {
            localMarketPriceTracker = true;

            this.localMarketPrice = param;
        }

        public boolean isNameSpecified() {
            return localNameTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getName() {
            return localName;
        }

        /**
         * Auto generated setter method
         * @param param Name
         */
        public void setName(java.lang.String param) {
            localNameTracker = true;

            this.localName = param;
        }

        public boolean isOtherFeeSpecified() {
            return localOtherFeeTracker;
        }

        /**
         * Auto generated getter method
         * @return java.math.BigDecimal
         */
        public java.math.BigDecimal getOtherFee() {
            return localOtherFee;
        }

        /**
         * Auto generated setter method
         * @param param OtherFee
         */
        public void setOtherFee(java.math.BigDecimal param) {
            localOtherFeeTracker = true;

            this.localOtherFee = param;
        }

        public boolean isPublishTimeSpecified() {
            return localPublishTimeTracker;
        }

        /**
         * Auto generated getter method
         * @return java.util.Date
         */
        public java.util.Date getPublishTime() {
            return localPublishTime;
        }

        /**
         * Auto generated setter method
         * @param param PublishTime
         */
        public void setPublishTime(java.util.Date param) {
            localPublishTimeTracker = true;

            this.localPublishTime = param;
        }

        public boolean isRelatedGoodsSpecified() {
            return localRelatedGoodsTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getRelatedGoods() {
            return localRelatedGoods;
        }

        /**
         * Auto generated setter method
         * @param param RelatedGoods
         */
        public void setRelatedGoods(java.lang.String param) {
            localRelatedGoodsTracker = true;

            this.localRelatedGoods = param;
        }

        public boolean isRemarkSpecified() {
            return localRemarkTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getRemark() {
            return localRemark;
        }

        /**
         * Auto generated setter method
         * @param param Remark
         */
        public void setRemark(java.lang.String param) {
            localRemarkTracker = true;

            this.localRemark = param;
        }

        public boolean isSaleNumSpecified() {
            return localSaleNumTracker;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getSaleNum() {
            return localSaleNum;
        }

        /**
         * Auto generated setter method
         * @param param SaleNum
         */
        public void setSaleNum(int param) {
            localSaleNumTracker = true;

            this.localSaleNum = param;
        }

        public boolean isSalePriceSpecified() {
            return localSalePriceTracker;
        }

        /**
         * Auto generated getter method
         * @return java.math.BigDecimal
         */
        public java.math.BigDecimal getSalePrice() {
            return localSalePrice;
        }

        /**
         * Auto generated setter method
         * @param param SalePrice
         */
        public void setSalePrice(java.math.BigDecimal param) {
            localSalePriceTracker = true;

            this.localSalePrice = param;
        }

        public boolean isSaleTimeSpecified() {
            return localSaleTimeTracker;
        }

        /**
         * Auto generated getter method
         * @return java.util.Date
         */
        public java.util.Date getSaleTime() {
            return localSaleTime;
        }

        /**
         * Auto generated setter method
         * @param param SaleTime
         */
        public void setSaleTime(java.util.Date param) {
            localSaleTimeTracker = true;

            this.localSaleTime = param;
        }

        public boolean isShowImageSpecified() {
            return localShowImageTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getShowImage() {
            return localShowImage;
        }

        /**
         * Auto generated setter method
         * @param param ShowImage
         */
        public void setShowImage(java.lang.String param) {
            localShowImageTracker = true;

            this.localShowImage = param;
        }

        public boolean isSoldOutTimeSpecified() {
            return localSoldOutTimeTracker;
        }

        /**
         * Auto generated getter method
         * @return java.util.Date
         */
        public java.util.Date getSoldOutTime() {
            return localSoldOutTime;
        }

        /**
         * Auto generated setter method
         * @param param SoldOutTime
         */
        public void setSoldOutTime(java.util.Date param) {
            localSoldOutTimeTracker = true;

            this.localSoldOutTime = param;
        }

        public boolean isStockSpecified() {
            return localStockTracker;
        }

        /**
         * Auto generated getter method
         * @return java.math.BigDecimal
         */
        public java.math.BigDecimal getStock() {
            return localStock;
        }

        /**
         * Auto generated setter method
         * @param param Stock
         */
        public void setStock(java.math.BigDecimal param) {
            localStockTracker = true;

            this.localStock = param;
        }

        public boolean isStockRulesIdSpecified() {
            return localStockRulesIdTracker;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getStockRulesId() {
            return localStockRulesId;
        }

        /**
         * Auto generated setter method
         * @param param StockRulesId
         */
        public void setStockRulesId(int param) {
            localStockRulesIdTracker = true;

            this.localStockRulesId = param;
        }

        public boolean isStockRulesNameSpecified() {
            return localStockRulesNameTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getStockRulesName() {
            return localStockRulesName;
        }

        /**
         * Auto generated setter method
         * @param param StockRulesName
         */
        public void setStockRulesName(java.lang.String param) {
            localStockRulesNameTracker = true;

            this.localStockRulesName = param;
        }

        public boolean isTemplateIdSpecified() {
            return localTemplateIdTracker;
        }

        /**
         * Auto generated getter method
         * @return int
         */
        public int getTemplateId() {
            return localTemplateId;
        }

        /**
         * Auto generated setter method
         * @param param TemplateId
         */
        public void setTemplateId(int param) {
            localTemplateIdTracker = true;

            this.localTemplateId = param;
        }

        public boolean isTypeSpecified() {
            return localTypeTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getType() {
            return localType;
        }

        /**
         * Auto generated setter method
         * @param param Type
         */
        public void setType(java.lang.String param) {
            localTypeTracker = true;

            this.localType = param;
        }

        public boolean isTypeNameSpecified() {
            return localTypeNameTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getTypeName() {
            return localTypeName;
        }

        /**
         * Auto generated setter method
         * @param param TypeName
         */
        public void setTypeName(java.lang.String param) {
            localTypeNameTracker = true;

            this.localTypeName = param;
        }

        public boolean isUomSpecified() {
            return localUomTracker;
        }

        /**
         * Auto generated getter method
         * @return java.lang.String
         */
        public java.lang.String getUom() {
            return localUom;
        }

        /**
         * Auto generated setter method
         * @param param Uom
         */
        public void setUom(java.lang.String param) {
            localUomTracker = true;

            this.localUom = param;
        }

        /**
         *
         * @param parentQName
         * @param factory
         * @return org.apache.axiom.om.OMElement
         */
        public org.apache.axiom.om.OMElement getOMElement(
            final javax.xml.namespace.QName parentQName,
            final org.apache.axiom.om.OMFactory factory)
            throws org.apache.axis2.databinding.ADBException {
            return factory.createOMElement(new org.apache.axis2.databinding.ADBDataSource(
                    this, parentQName));
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            serialize(parentQName, xmlWriter, false);
        }

        public void serialize(final javax.xml.namespace.QName parentQName,
            javax.xml.stream.XMLStreamWriter xmlWriter, boolean serializeType)
            throws javax.xml.stream.XMLStreamException,
                org.apache.axis2.databinding.ADBException {
            java.lang.String prefix = null;
            java.lang.String namespace = null;

            prefix = parentQName.getPrefix();
            namespace = parentQName.getNamespaceURI();
            writeStartElement(prefix, namespace, parentQName.getLocalPart(),
                xmlWriter);

            if (serializeType) {
                java.lang.String namespacePrefix = registerPrefix(xmlWriter,
                        "http://model.eGoods.topie.com/xsd");

                if ((namespacePrefix != null) &&
                        (namespacePrefix.trim().length() > 0)) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "type",
                        namespacePrefix + ":EGoods", xmlWriter);
                } else {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "type",
                        "EGoods", xmlWriter);
                }
            }

            if (localAfterServiceTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "afterService", xmlWriter);

                if (localAfterService == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localAfterService);
                }

                xmlWriter.writeEndElement();
            }

            if (localBuyPriceTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "buyPrice", xmlWriter);

                if (localBuyPrice == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localBuyPrice));
                }

                xmlWriter.writeEndElement();
            }

            if (localDeleteFlagTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "deleteFlag", xmlWriter);

                if (localDeleteFlag == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localDeleteFlag);
                }

                xmlWriter.writeEndElement();
            }

            if (localDetailTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "detail", xmlWriter);

                if (localDetail == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localDetail);
                }

                xmlWriter.writeEndElement();
            }

            if (localDynamicStateTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "dynamicState", xmlWriter);

                if (localDynamicState == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localDynamicState);
                }

                xmlWriter.writeEndElement();
            }

            if (localGoodsIdTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "goodsId", xmlWriter);

                if (localGoodsId == java.lang.Integer.MIN_VALUE) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localGoodsId));
                }

                xmlWriter.writeEndElement();
            }

            if (localGoodsNoTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "goodsNo", xmlWriter);

                if (localGoodsNo == java.lang.Integer.MIN_VALUE) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localGoodsNo));
                }

                xmlWriter.writeEndElement();
            }

            if (localIdTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "id", xmlWriter);

                if (localId == java.lang.Integer.MIN_VALUE) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localId));
                }

                xmlWriter.writeEndElement();
            }

            if (localImagesTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "images", xmlWriter);

                if (localImages == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localImages);
                }

                xmlWriter.writeEndElement();
            }

            if (localInfoTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "info", xmlWriter);

                if (localInfo == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localInfo);
                }

                xmlWriter.writeEndElement();
            }

            if (localIntegralTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "integral", xmlWriter);

                if (localIntegral == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localIntegral));
                }

                xmlWriter.writeEndElement();
            }

            if (localIsPresellTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "isPresell", xmlWriter);

                if (false) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localIsPresell));
                }

                xmlWriter.writeEndElement();
            }

            if (localIsSaleTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "isSale", xmlWriter);

                if (false) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localIsSale));
                }

                xmlWriter.writeEndElement();
            }

            if (localKeywordsTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "keywords", xmlWriter);

                if (localKeywords == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localKeywords);
                }

                xmlWriter.writeEndElement();
            }

            if (localMarketPriceTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "marketPrice", xmlWriter);

                if (localMarketPrice == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localMarketPrice));
                }

                xmlWriter.writeEndElement();
            }

            if (localNameTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "name", xmlWriter);

                if (localName == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localName);
                }

                xmlWriter.writeEndElement();
            }

            if (localOtherFeeTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "otherFee", xmlWriter);

                if (localOtherFee == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localOtherFee));
                }

                xmlWriter.writeEndElement();
            }

            if (localPublishTimeTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "publishTime", xmlWriter);

                if (localPublishTime == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localPublishTime));
                }

                xmlWriter.writeEndElement();
            }

            if (localRelatedGoodsTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "relatedGoods", xmlWriter);

                if (localRelatedGoods == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localRelatedGoods);
                }

                xmlWriter.writeEndElement();
            }

            if (localRemarkTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "remark", xmlWriter);

                if (localRemark == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localRemark);
                }

                xmlWriter.writeEndElement();
            }

            if (localSaleNumTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "saleNum", xmlWriter);

                if (localSaleNum == java.lang.Integer.MIN_VALUE) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localSaleNum));
                }

                xmlWriter.writeEndElement();
            }

            if (localSalePriceTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "salePrice", xmlWriter);

                if (localSalePrice == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localSalePrice));
                }

                xmlWriter.writeEndElement();
            }

            if (localSaleTimeTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "saleTime", xmlWriter);

                if (localSaleTime == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localSaleTime));
                }

                xmlWriter.writeEndElement();
            }

            if (localShowImageTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "showImage", xmlWriter);

                if (localShowImage == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localShowImage);
                }

                xmlWriter.writeEndElement();
            }

            if (localSoldOutTimeTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "soldOutTime", xmlWriter);

                if (localSoldOutTime == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localSoldOutTime));
                }

                xmlWriter.writeEndElement();
            }

            if (localStockTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "stock", xmlWriter);

                if (localStock == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localStock));
                }

                xmlWriter.writeEndElement();
            }

            if (localStockRulesIdTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "stockRulesId", xmlWriter);

                if (localStockRulesId == java.lang.Integer.MIN_VALUE) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localStockRulesId));
                }

                xmlWriter.writeEndElement();
            }

            if (localStockRulesNameTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "stockRulesName", xmlWriter);

                if (localStockRulesName == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localStockRulesName);
                }

                xmlWriter.writeEndElement();
            }

            if (localTemplateIdTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "templateId", xmlWriter);

                if (localTemplateId == java.lang.Integer.MIN_VALUE) {
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            localTemplateId));
                }

                xmlWriter.writeEndElement();
            }

            if (localTypeTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "type", xmlWriter);

                if (localType == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localType);
                }

                xmlWriter.writeEndElement();
            }

            if (localTypeNameTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "typeName", xmlWriter);

                if (localTypeName == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localTypeName);
                }

                xmlWriter.writeEndElement();
            }

            if (localUomTracker) {
                namespace = "http://model.eGoods.topie.com/xsd";
                writeStartElement(null, namespace, "uom", xmlWriter);

                if (localUom == null) {
                    // write the nil attribute
                    writeAttribute("xsi",
                        "http://www.w3.org/2001/XMLSchema-instance", "nil",
                        "1", xmlWriter);
                } else {
                    xmlWriter.writeCharacters(localUom);
                }

                xmlWriter.writeEndElement();
            }

            xmlWriter.writeEndElement();
        }

        private static java.lang.String generatePrefix(
            java.lang.String namespace) {
            if (namespace.equals("http://model.eGoods.topie.com/xsd")) {
                return "ns1";
            }

            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        /**
         * Utility method to write an element start tag.
         */
        private void writeStartElement(java.lang.String prefix,
            java.lang.String namespace, java.lang.String localPart,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

            if (writerPrefix != null) {
                xmlWriter.writeStartElement(writerPrefix, localPart, namespace);
            } else {
                if (namespace.length() == 0) {
                    prefix = "";
                } else if (prefix == null) {
                    prefix = generatePrefix(namespace);
                }

                xmlWriter.writeStartElement(prefix, localPart, namespace);
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }
        }

        /**
         * Util method to write an attribute with the ns prefix
         */
        private void writeAttribute(java.lang.String prefix,
            java.lang.String namespace, java.lang.String attName,
            java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);

            if (writerPrefix != null) {
                xmlWriter.writeAttribute(writerPrefix, namespace, attName,
                    attValue);
            } else {
                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
                xmlWriter.writeAttribute(prefix, namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeAttribute(java.lang.String namespace,
            java.lang.String attName, java.lang.String attValue,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attValue);
            } else {
                xmlWriter.writeAttribute(registerPrefix(xmlWriter, namespace),
                    namespace, attName, attValue);
            }
        }

        /**
         * Util method to write an attribute without the ns prefix
         */
        private void writeQNameAttribute(java.lang.String namespace,
            java.lang.String attName, javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String attributeNamespace = qname.getNamespaceURI();
            java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);

            if (attributePrefix == null) {
                attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
            }

            java.lang.String attributeValue;

            if (attributePrefix.trim().length() > 0) {
                attributeValue = attributePrefix + ":" + qname.getLocalPart();
            } else {
                attributeValue = qname.getLocalPart();
            }

            if (namespace.equals("")) {
                xmlWriter.writeAttribute(attName, attributeValue);
            } else {
                registerPrefix(xmlWriter, namespace);
                xmlWriter.writeAttribute(attributePrefix, namespace, attName,
                    attributeValue);
            }
        }

        /**
         *  method to handle Qnames
         */
        private void writeQName(javax.xml.namespace.QName qname,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();

            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);

                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix, namespaceURI);
                }

                if (prefix.trim().length() > 0) {
                    xmlWriter.writeCharacters(prefix + ":" +
                        org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                            qname));
                }
            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                        qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
            javax.xml.stream.XMLStreamWriter xmlWriter)
            throws javax.xml.stream.XMLStreamException {
            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }

                    namespaceURI = qnames[i].getNamespaceURI();

                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);

                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix, namespaceURI);
                        }

                        if (prefix.trim().length() > 0) {
                            stringToWrite.append(prefix).append(":")
                                         .append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                qnames[i]));
                    }
                }

                xmlWriter.writeCharacters(stringToWrite.toString());
            }
        }

        /**
         * Register a namespace prefix
         */
        private java.lang.String registerPrefix(
            javax.xml.stream.XMLStreamWriter xmlWriter,
            java.lang.String namespace)
            throws javax.xml.stream.XMLStreamException {
            java.lang.String prefix = xmlWriter.getPrefix(namespace);

            if (prefix == null) {
                prefix = generatePrefix(namespace);

                javax.xml.namespace.NamespaceContext nsContext = xmlWriter.getNamespaceContext();

                while (true) {
                    java.lang.String uri = nsContext.getNamespaceURI(prefix);

                    if ((uri == null) || (uri.length() == 0)) {
                        break;
                    }

                    prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                }

                xmlWriter.writeNamespace(prefix, namespace);
                xmlWriter.setPrefix(prefix, namespace);
            }

            return prefix;
        }

        /**
         *  Factory class that keeps the parse method
         */
        public static class Factory {
            private static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Factory.class);

            /**
             * static method to create the object
             * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
             *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
             * Postcondition: If this object is an element, the reader is positioned at its end element
             *                If this object is a complex type, the reader is positioned at the end element of its outer element
             */
            public static EGoods parse(javax.xml.stream.XMLStreamReader reader)
                throws java.lang.Exception {
                EGoods object = new EGoods();

                int event;
                javax.xml.namespace.QName currentQName = null;
                java.lang.String nillableValue = null;
                java.lang.String prefix = "";
                java.lang.String namespaceuri = "";

                try {
                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    currentQName = reader.getName();

                    if (reader.getAttributeValue(
                                "http://www.w3.org/2001/XMLSchema-instance",
                                "type") != null) {
                        java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "type");

                        if (fullTypeName != null) {
                            java.lang.String nsPrefix = null;

                            if (fullTypeName.indexOf(":") > -1) {
                                nsPrefix = fullTypeName.substring(0,
                                        fullTypeName.indexOf(":"));
                            }

                            nsPrefix = (nsPrefix == null) ? "" : nsPrefix;

                            java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(
                                        ":") + 1);

                            if (!"EGoods".equals(type)) {
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext()
                                                               .getNamespaceURI(nsPrefix);

                                return (EGoods) ExtensionMapper.getTypeObject(nsUri,
                                    type, reader);
                            }
                        }
                    }

                    // Note all attributes that were handled. Used to differ normal attributes
                    // from anyAttributes.
                    java.util.Vector handledAttributes = new java.util.Vector();

                    reader.next();

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd",
                                "afterService").equals(reader.getName())) ||
                            new javax.xml.namespace.QName("", "afterService").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setAfterService(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "buyPrice").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "buyPrice").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setBuyPrice(org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd",
                                "deleteFlag").equals(reader.getName())) ||
                            new javax.xml.namespace.QName("", "deleteFlag").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setDeleteFlag(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "detail").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "detail").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setDetail(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd",
                                "dynamicState").equals(reader.getName())) ||
                            new javax.xml.namespace.QName("", "dynamicState").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setDynamicState(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "goodsId").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "goodsId").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setGoodsId(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                    content));
                        } else {
                            object.setGoodsId(java.lang.Integer.MIN_VALUE);

                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setGoodsId(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "goodsNo").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "goodsNo").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setGoodsNo(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                    content));
                        } else {
                            object.setGoodsNo(java.lang.Integer.MIN_VALUE);

                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setGoodsNo(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "id").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "id").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setId(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                    content));
                        } else {
                            object.setId(java.lang.Integer.MIN_VALUE);

                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setId(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "images").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "images").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setImages(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "info").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "info").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setInfo(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "integral").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "integral").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setIntegral(org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "isPresell").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "isPresell").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setIsPresell(org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "isSale").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "isSale").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setIsSale(org.apache.axis2.databinding.utils.ConverterUtil.convertToBoolean(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "keywords").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "keywords").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setKeywords(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd",
                                "marketPrice").equals(reader.getName())) ||
                            new javax.xml.namespace.QName("", "marketPrice").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setMarketPrice(org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "name").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "name").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "otherFee").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "otherFee").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setOtherFee(org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd",
                                "publishTime").equals(reader.getName())) ||
                            new javax.xml.namespace.QName("", "publishTime").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setPublishTime(org.apache.axis2.databinding.utils.ConverterUtil.convertToDate(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd",
                                "relatedGoods").equals(reader.getName())) ||
                            new javax.xml.namespace.QName("", "relatedGoods").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setRelatedGoods(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "remark").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "remark").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setRemark(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "saleNum").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "saleNum").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setSaleNum(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                    content));
                        } else {
                            object.setSaleNum(java.lang.Integer.MIN_VALUE);

                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setSaleNum(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "salePrice").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "salePrice").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setSalePrice(org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "saleTime").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "saleTime").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setSaleTime(org.apache.axis2.databinding.utils.ConverterUtil.convertToDate(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "showImage").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "showImage").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setShowImage(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd",
                                "soldOutTime").equals(reader.getName())) ||
                            new javax.xml.namespace.QName("", "soldOutTime").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setSoldOutTime(org.apache.axis2.databinding.utils.ConverterUtil.convertToDate(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "stock").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "stock").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setStock(org.apache.axis2.databinding.utils.ConverterUtil.convertToDecimal(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd",
                                "stockRulesId").equals(reader.getName())) ||
                            new javax.xml.namespace.QName("", "stockRulesId").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setStockRulesId(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                    content));
                        } else {
                            object.setStockRulesId(java.lang.Integer.MIN_VALUE);

                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setStockRulesId(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd",
                                "stockRulesName").equals(reader.getName())) ||
                            new javax.xml.namespace.QName("", "stockRulesName").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setStockRulesName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd",
                                "templateId").equals(reader.getName())) ||
                            new javax.xml.namespace.QName("", "templateId").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setTemplateId(org.apache.axis2.databinding.utils.ConverterUtil.convertToInt(
                                    content));
                        } else {
                            object.setTemplateId(java.lang.Integer.MIN_VALUE);

                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                        object.setTemplateId(java.lang.Integer.MIN_VALUE);
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "type").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "type").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setType(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "typeName").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "typeName").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setTypeName(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if ((reader.isStartElement() &&
                            new javax.xml.namespace.QName(
                                "http://model.eGoods.topie.com/xsd", "uom").equals(
                                reader.getName())) ||
                            new javax.xml.namespace.QName("", "uom").equals(
                                reader.getName())) {
                        nillableValue = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                                "nil");

                        if (!"true".equals(nillableValue) &&
                                !"1".equals(nillableValue)) {
                            java.lang.String content = reader.getElementText();

                            object.setUom(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(
                                    content));
                        } else {
                            reader.getElementText(); // throw away text nodes if any.
                        }

                        reader.next();
                    } // End of if for expected property start element

                    else {
                    }

                    while (!reader.isStartElement() && !reader.isEndElement())
                        reader.next();

                    if (reader.isStartElement()) {
                        // 2 - A start element we are not expecting indicates a trailing invalid property
                        throw new org.apache.axis2.databinding.ADBException(
                            "Unexpected subelement " + reader.getName());
                    }
                } catch (javax.xml.stream.XMLStreamException e) {
                    throw new java.lang.Exception(e);
                }

                return object;
            }
        } //end of factory class

		@Override
		public XMLStreamReader getPullParser(QName arg0)
				throws XMLStreamException {
			// TODO Auto-generated method stub
			return null;
		}
    }
}
