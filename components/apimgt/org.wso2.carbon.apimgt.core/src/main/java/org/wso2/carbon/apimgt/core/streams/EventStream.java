/*
 *
 *   Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.carbon.apimgt.core.streams;

import org.wso2.carbon.apimgt.core.models.Endpoint;
import java.util.*;

/**
 * Representation of an Stream object. Only immutable instances of this class can be created via the provided inner static
 * {@code APIBuilder} class which implements the builder pattern as outlined in "Effective Java 2nd Edition
 * by Joshua Bloch(Item 2)"
 */

public class EventStream {
    private String id;
    private String provider;
    private String name;
    private String version;
    private String description;
    private String lifeCycleStatus;
    private Map<String, Endpoint> endpoint;
    private Set<String> streamType;
    private List<Authorization> streamAuthorization;
    private EventStream.Visibility visibility;

    private boolean isProducable;
    private boolean canProducerAccessDirectly;
    private boolean canProducerAccessViaGateway;
    private List<Authorization> producerAuthorization;
    private List<Transport> producerTransport;
    private List<MessageType> producerMessageType;

    private boolean isConsumable;
    private boolean canConsumerAccessDirectly;
    private boolean canConsumerAccessViaGateway;
    private List<Authorization> consumerAuthorization;
    private List<Transport> consumerTransport;
    private List<Display> consumerDisplay;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLifeCycleStatus() {
        return lifeCycleStatus;
    }

    public void setLifeCycleStatus(String lifeCycleStatus) {
        this.lifeCycleStatus = lifeCycleStatus;
    }

    public Map<String, Endpoint> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Map<String, Endpoint> endpoint) {
        this.endpoint = endpoint;
    }

    public Set<String> getStreamType() {
        return streamType;
    }

    public void setStreamType(Set<String> streamType) {
        this.streamType = streamType;
    }

    public List<Authorization> getStreamAuthorization() {
        return streamAuthorization;
    }

    public void setStreamAuthorization(List<Authorization> streamAuthorization) {
        this.streamAuthorization = streamAuthorization;
    }


    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public boolean isProducable() {
        return isProducable;
    }

    public void setProducable(boolean producable) {
        isProducable = producable;
    }

    public boolean isCanProducerAccessDirectly() {
        return canProducerAccessDirectly;
    }

    public void setCanProducerAccessDirectly(boolean canProducerAccessDirectly) {
        this.canProducerAccessDirectly = canProducerAccessDirectly;
    }

    public boolean isCanProducerAccessViaGateway() {
        return canProducerAccessViaGateway;
    }

    public void setCanProducerAccessViaGateway(boolean canProducerAccessViaGateway) {
        this.canProducerAccessViaGateway = canProducerAccessViaGateway;
    }

    public List<Authorization> getProducerAuthorization() {
        return producerAuthorization;
    }

    public void setProducerAuthorization(List<Authorization> producerAuthorization) {
        this.producerAuthorization = producerAuthorization;
    }

    public List<Transport> getProducerTransport() {
        return producerTransport;
    }

    public void setProducerTransport(List<Transport> producerTransport) {
        this.producerTransport = producerTransport;
    }

    public List<MessageType> getProducerMessageType() {
        return producerMessageType;
    }

    public void setProducerMessageType(List<MessageType> producerMessageType) {
        this.producerMessageType = producerMessageType;
    }

    public boolean isConsumable() {
        return isConsumable;
    }

    public void setConsumable(boolean consumable) {
        isConsumable = consumable;
    }

    public boolean isCanConsumerAccessDirectly() {
        return canConsumerAccessDirectly;
    }

    public void setCanConsumerAccessDirectly(boolean canConsumerAccessDirectly) {
        this.canConsumerAccessDirectly = canConsumerAccessDirectly;
    }

    public boolean isCanConsumerAccessViaGateway() {
        return canConsumerAccessViaGateway;
    }

    public void setCanConsumerAccessViaGateway(boolean canConsumerAccessViaGateway) {
        this.canConsumerAccessViaGateway = canConsumerAccessViaGateway;
    }

    public List<Authorization> getConsumerAuthorization() {
        return consumerAuthorization;
    }

    public void setConsumerAuthorization(List<Authorization> consumerAuthorization) {
        this.consumerAuthorization = consumerAuthorization;
    }

    public List<Transport> getConsumerTransport() {
        return consumerTransport;
    }

    public void setConsumerTransport(List<Transport> consumerTransport) {
        this.consumerTransport = consumerTransport;
    }

    public List<Display> getConsumerDisplay() {
        return consumerDisplay;
    }

    public void setConsumerDisplay(List<Display> consumerDisplay) {
        this.consumerDisplay = consumerDisplay;
    }



    private EventStream(StreamBuilder builder) {

        id = builder.id;
        provider = builder.provider;
        name = builder.name;
        version = builder.version;
        description = builder.description;
        lifeCycleStatus = builder.lifeCycleStatus;
        endpoint = builder.endpoint;
        streamType = builder.streamType;
        streamAuthorization = builder.streamAuthorization;
        visibility = builder.visibility;
        isProducable = builder.isProducable;
        canProducerAccessDirectly = builder.canProducerAccessDirectly;
        canProducerAccessViaGateway = builder.canProducerAccessViaGateway;
        producerAuthorization = builder.producerAuthorization;
        producerTransport = builder.producerTransport;
        producerMessageType = builder.producerMessageType;
        isConsumable = builder.isConsumable;
        canConsumerAccessDirectly = builder.canConsumerAccessDirectly;
        canConsumerAccessViaGateway = builder.canConsumerAccessViaGateway;
        consumerAuthorization = builder.consumerAuthorization;
        consumerTransport = builder.consumerTransport;
        consumerDisplay = builder.consumerDisplay;
    }

    public enum Visibility {
        PUBLIC, PRIVATE, RESTRICTED,
    }

    public enum Transport {
        HTTP, WEBSOCKET, JMS, MQTT
    }

    public enum MessageType {
        TEXT, JSON, XML
    }

    public enum Authorization {
        BASICAUTH, OAUTH, CUSTOM
    }

    public enum Display {
        POLL, PUSH, CALLBACK
    }


    public static final class StreamBuilder {
        private String id;
        private String provider;
        private String name;
        private String version;
        private String description;
        private String lifeCycleStatus;
        private Map<String, Endpoint> endpoint = Collections.EMPTY_MAP;
        private Set<String> streamType = Collections.emptySet();
        private List<Authorization> streamAuthorization = new ArrayList<Authorization>();
        private EventStream.Visibility visibility = EventStream.Visibility.PUBLIC;

        private boolean isProducable;
        private boolean canProducerAccessDirectly;
        private boolean canProducerAccessViaGateway;
        private List<Authorization> producerAuthorization = new ArrayList<Authorization>();
        private List<Transport> producerTransport = new ArrayList<Transport>();
        private List<MessageType> producerMessageType = new ArrayList<MessageType>();

        private boolean isConsumable;
        private boolean canConsumerAccessDirectly;
        private boolean canConsumerAccessViaGateway;
        private List<Authorization> consumerAuthorization = new ArrayList<Authorization>();
        private List<Transport> consumerTransport = new ArrayList<Transport>();
        private List<Display> consumerDisplay = new ArrayList<Display>();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProvider() {
            return provider;
        }

        public void setProvider(String provider) {
            this.provider = provider;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLifeCycleStatus() {
            return lifeCycleStatus;
        }

        public void setLifeCycleStatus(String lifeCycleStatus) {
            this.lifeCycleStatus = lifeCycleStatus;
        }


        public Map<String, Endpoint> getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(Map<String, Endpoint> endpoint) {
            this.endpoint = endpoint;
        }

        public Set<String> getStreamType() {
            return streamType;
        }

        public void setStreamType(Set<String> streamType) {
            this.streamType = streamType;
        }

        public List<Authorization> getStreamAuthorization() {
            return streamAuthorization;
        }

        public void setStreamAuthorization(List<Authorization> streamAuthorization) {
            this.streamAuthorization = streamAuthorization;
        }

        public Visibility getVisibility() {
            return visibility;
        }

        public void setVisibility(Visibility visibility) {
            this.visibility = visibility;
        }

        public boolean isProducable() {
            return isProducable;
        }

        public void setProducable(boolean producable) {
            isProducable = producable;
        }

        public boolean isCanProducerAccessDirectly() {
            return canProducerAccessDirectly;
        }

        public void setCanProducerAccessDirectly(boolean canProducerAccessDirectly) {
            this.canProducerAccessDirectly = canProducerAccessDirectly;
        }

        public boolean isCanProducerAccessViaGateway() {
            return canProducerAccessViaGateway;
        }

        public void setCanProducerAccessViaGateway(boolean canProducerAccessViaGateway) {
            this.canProducerAccessViaGateway = canProducerAccessViaGateway;
        }

        public List<Authorization> getProducerAuthorization() {
            return producerAuthorization;
        }

        public void setProducerAuthorization(List<Authorization> producerAuthorization) {
            this.producerAuthorization = producerAuthorization;
        }

        public List<Transport> getProducerTransport() {
            return producerTransport;
        }

        public void setProducerTransport(List<Transport> producerTransport) {
            this.producerTransport = producerTransport;
        }

        public List<MessageType> getProducerMessageType() {
            return producerMessageType;
        }

        public void setProducerMessageType(List<MessageType> producerMessageType) {
            this.producerMessageType = producerMessageType;
        }

        public boolean isConsumable() {
            return isConsumable;
        }

        public void setConsumable(boolean consumable) {
            isConsumable = consumable;
        }

        public boolean isCanConsumerAccessDirectly() {
            return canConsumerAccessDirectly;
        }

        public void setCanConsumerAccessDirectly(boolean canConsumerAccessDirectly) {
            this.canConsumerAccessDirectly = canConsumerAccessDirectly;
        }

        public boolean isCanConsumerAccessViaGateway() {
            return canConsumerAccessViaGateway;
        }

        public void setCanConsumerAccessViaGateway(boolean canConsumerAccessViaGateway) {
            this.canConsumerAccessViaGateway = canConsumerAccessViaGateway;
        }

        public List<Authorization> getConsumerAuthorization() {
            return consumerAuthorization;
        }

        public void setConsumerAuthorization(List<Authorization> consumerAuthorization) {
            this.consumerAuthorization = consumerAuthorization;
        }

        public List<Transport> getConsumerTransport() {
            return consumerTransport;
        }

        public void setConsumerTransport(List<Transport> consumerTransport) {
            this.consumerTransport = consumerTransport;
        }

        public List<Display> getConsumerDisplay() {
            return consumerDisplay;
        }

        public void setConsumerDisplay(List<Display> consumerDisplay) {
            this.consumerDisplay = consumerDisplay;
        }


        public StreamBuilder(FileStream stream) {

            id = stream.getId();
            provider = stream.getProvider();
            name = stream.getName();
            version = stream.getVersion();
            description = stream.getDescription();
            lifeCycleStatus = stream.getLifeCycleStatus();
            endpoint = stream.getEndpoint();
            streamType = stream.getStreamType();
            streamAuthorization = stream.getStreamAuthorization();
            visibility = stream.getVisibility();
            isProducable = stream.isProducable();
            canProducerAccessDirectly = stream.isCanProducerAccessDirectly();
            canProducerAccessViaGateway = stream.isCanProducerAccessViaGateway();
            producerAuthorization = stream.getProducerAuthorization();
            producerTransport = stream.getProducerTransport();
            producerMessageType = stream.getProducerMessageType();
            isConsumable = stream.isConsumable();
            canConsumerAccessDirectly = stream.isCanConsumerAccessDirectly();
            canConsumerAccessViaGateway = stream.isCanConsumerAccessViaGateway();
            consumerAuthorization = stream.getConsumerAuthorization();
            consumerTransport = stream.getConsumerTransport();
            consumerDisplay = stream.getConsumerDisplay();
        }

        public StreamBuilder(String streamId, String name, String provider, String version) {
            this.id = streamId;
            this.name = name;
            this.provider = provider;
            this.version = version;
        }


        public StreamBuilder(EventStream copy) {
            this.id = copy.id;
            this.provider = copy.provider;
            this.name = copy.name;
            this.version = copy.version;
            this.description = copy.description;
            this.lifeCycleStatus = copy.lifeCycleStatus;
            this.endpoint = copy.endpoint;
            this.streamType = copy.streamType;
            this.streamAuthorization = copy.streamAuthorization;
            this.visibility = copy.visibility;
            this.isProducable = copy.isProducable;
            this.canProducerAccessDirectly = copy.canProducerAccessDirectly;
            this.canProducerAccessViaGateway = copy.canProducerAccessViaGateway;
            this.producerAuthorization = copy.producerAuthorization;
            this.producerTransport = copy.producerTransport;
            this.producerMessageType = copy.producerMessageType;
            this.isConsumable = copy.isConsumable;
            this.canConsumerAccessDirectly = copy.canConsumerAccessDirectly;
            this.canConsumerAccessViaGateway = copy.canConsumerAccessViaGateway;
            this.consumerAuthorization = copy.consumerAuthorization;
            this.consumerTransport = copy.consumerTransport;
            this.consumerDisplay = copy.consumerDisplay;

        }

        /**
         * Sets the {@code id} and returns a reference to this StreamBuilder so that the methods can be chained together.
         *
         * @param id the {@code id} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder id(String id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the {@code name} and returns a reference to this StreamBuilder so that the methods can be chained together.
         *
         * @param name the {@code name} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the {@code provider} and returns a reference to this StreamBuilder so that the methods can be chained together.
         *
         * @param provider the {@code name} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder provider(String provider) {
            this.provider = provider;
            return this;
        }

        /**
         * Sets the {@code version} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param version the {@code version} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder version(String version) {
            this.version = version;
            return this;
        }


        /**
         * Sets the {@code description} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param description the {@code description} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the {@code lifeCycleStatus} and returns a reference to this StreamBuilder so that the methods can be
         * chained together.
         *
         * @param lifeCycleStatus the {@code lifeCycleStatus} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder lifeCycleStatus(String lifeCycleStatus) {
            this.lifeCycleStatus = lifeCycleStatus;
            return this;
        }

        /**
         * Sets the {@code endpoint} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param endpoint the {@code endpoint} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder endpoint(Map<String, Endpoint> endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        /**
         * Sets the {@code streamType} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param streamType the {@code streamType} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder streamType(Set<String> streamType) {
            this.streamType = streamType;
            return this;
        }

        /**
         * Sets the {@code streamAuthorization} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param streamAuthorization the {@code streamAuthorization} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder streamAuthorization(List<Authorization> streamAuthorization) {
            this.streamAuthorization = streamAuthorization;
            return this;
        }

        /**
         * Sets the {@code visibility} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param visibility the {@code visibility} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder visibility(EventStream.Visibility visibility) {
            this.visibility = visibility;
            return this;
        }

        /**
         * Sets the {@code isProducable} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param isProducable the {@code isProducable} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder isProducable(boolean isProducable) {
            this.isProducable = isProducable;
            return this;
        }

        /**
         * Sets the {@code canProducerAccessDirectly} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param canProducerAccessDirectly the {@code canProducerAccessDirectly} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder canProducerAccessDirectly(boolean canProducerAccessDirectly) {
            this.canProducerAccessDirectly = canProducerAccessDirectly;
            return this;
        }

        /**
         * Sets the {@code canProducerAccessViaGateway} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param canProducerAccessViaGateway the {@code canProducerAccessViaGateway} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder canProducerAccessViaGateway(boolean canProducerAccessViaGateway) {
            this.canProducerAccessViaGateway = canProducerAccessViaGateway;
            return this;
        }

        /**
         * Sets the {@code producerAuthorization} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param producerAuthorization the {@code producerAuthorization} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder producerAuthorization(List<Authorization> producerAuthorization) {
            this.producerAuthorization = producerAuthorization;
            return this;
        }

        /**
         * Sets the {@code producerTransport} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param producerTransport the {@code producerTransport} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder producerTransport(List<Transport> producerTransport) {
            this.producerTransport = producerTransport;
            return this;
        }

        /**
         * Sets the {@code producerMessageType} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param producerMessageType the {@code producerMessageType} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder producerMessageType(List<MessageType> producerMessageType) {
            this.producerMessageType = producerMessageType;
            return this;
        }

        /**
         * Sets the {@code isConsumable} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param isConsumable the {@code isConsumable} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder isConsumable(boolean isConsumable) {
            this.isConsumable = isConsumable;
            return this;
        }

        /**
         * Sets the {@code canConsumerAccessDirectly} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param canConsumerAccessDirectly the {@code canConsumerAccessDirectly} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder canConsumerAccessDirectly(boolean canConsumerAccessDirectly) {
            this.canConsumerAccessDirectly = canConsumerAccessDirectly;
            return this;
        }

        /**
         * Sets the {@code canConsumerAccessViaGateway} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param canConsumerAccessViaGateway the {@code canConsumerAccessViaGateway} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder canConsumerAccessViaGateway(boolean canConsumerAccessViaGateway) {
            this.canConsumerAccessViaGateway = canConsumerAccessViaGateway;
            return this;
        }

        /**
         * Sets the {@code consumerAuthorization} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param consumerAuthorization the {@code consumerAuthorization} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder consumerAuthorization(List<Authorization> consumerAuthorization) {
            this.consumerAuthorization = consumerAuthorization;
            return this;
        }

        /**
         * Sets the {@code consumerTransport} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param consumerTransport the {@code consumerTransport} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder consumerTransport(List<Transport> consumerTransport) {
            this.consumerTransport = consumerTransport;
            return this;
        }

        /**
         * Sets the {@code consumerDisplay} and returns a reference to this StreamBuilder so that the methods can be chained
         * together.
         *
         * @param consumerDisplay the {@code consumerDisplay} to set
         * @return a reference to this StreamBuilder
         */
        public StreamBuilder consumerDisplay(List<Display> consumerDisplay) {
            this.consumerDisplay = consumerDisplay;
            return this;
        }


        public EventStream build() {
            return new EventStream(this);
        }


    }



}
