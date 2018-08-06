package org.wso2.carbon.apimgt.core.streams;

import org.wso2.carbon.apimgt.core.models.Endpoint;
import org.wso2.carbon.apimgt.core.models.UriTemplate;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileStream {

    private String id;
    private String provider;
    private String name;
    private String version;
    private String description;
    private String lifeCycleStatus;
    private Map<String, Endpoint> endpoint;
    private Set<String> streamType;
    private List<EventStream.Authorization> streamAuthorization;
    private EventStream.Visibility visibility;

    private boolean isProducable;
    private boolean canProducerAccessDirectly;
    private boolean canProducerAccessViaGateway;
    private List<EventStream.Authorization> producerAuthorization;
    private List<EventStream.Transport> producerTransport;
    private List<EventStream.MessageType> producerMessageType;

    private boolean isConsumable;
    private boolean canConsumerAccessDirectly;
    private boolean canConsumerAccessViaGateway;
    private List<EventStream.Authorization> consumerAuthorization;
    private List<EventStream.Transport> consumerTransport;
    private List<EventStream.Display> consumerDisplay;


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

    public List<EventStream.Authorization> getStreamAuthorization() {
        return streamAuthorization;
    }

    public void setStreamAuthorization(List<EventStream.Authorization> streamAuthorization) {
        this.streamAuthorization = streamAuthorization;
    }

    public EventStream.Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(EventStream.Visibility visibility) {
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

    public List<EventStream.Authorization> getProducerAuthorization() {
        return producerAuthorization;
    }

    public void setProducerAuthorization(List<EventStream.Authorization> producerAuthorization) {
        this.producerAuthorization = producerAuthorization;
    }

    public List<EventStream.Transport> getProducerTransport() {
        return producerTransport;
    }

    public void setProducerTransport(List<EventStream.Transport> producerTransport) {
        this.producerTransport = producerTransport;
    }

    public List<EventStream.MessageType> getProducerMessageType() {
        return producerMessageType;
    }

    public void setProducerMessageType(List<EventStream.MessageType> producerMessageType) {
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

    public List<EventStream.Authorization> getConsumerAuthorization() {
        return consumerAuthorization;
    }

    public void setConsumerAuthorization(List<EventStream.Authorization> consumerAuthorization) {
        this.consumerAuthorization = consumerAuthorization;
    }

    public List<EventStream.Transport> getConsumerTransport() {
        return consumerTransport;
    }

    public void setConsumerTransport(List<EventStream.Transport> consumerTransport) {
        this.consumerTransport = consumerTransport;
    }

    public List<EventStream.Display> getConsumerDisplay() {
        return consumerDisplay;
    }

    public void setConsumerDisplay(List<EventStream.Display> consumerDisplay) {
        this.consumerDisplay = consumerDisplay;
    }


    public FileStream(EventStream stream) {
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


    /**
     * Representation of Uritemplate for file system
     */
    public static class FileUriTemplate {
        private String templateId;
        private String uriTemplate;
        private String httpVerb;
        private String authType;
        private String policy;
        private Map<String, Endpoint> endpoint;

        public String getTemplateId() {
            return templateId;
        }

        public void setTemplateId(String templateId) {
            this.templateId = templateId;
        }

        public String getUriTemplate() {
            return uriTemplate;
        }

        public void setUriTemplate(String uriTemplate) {
            this.uriTemplate = uriTemplate;
        }

        public String getHttpVerb() {
            return httpVerb;
        }

        public void setHttpVerb(String httpVerb) {
            this.httpVerb = httpVerb;
        }

        public String getAuthType() {
            return authType;
        }

        public void setAuthType(String authType) {
            this.authType = authType;
        }

        public String getPolicy() {
            return policy;
        }

        public void setPolicy(String policy) {
            this.policy = policy;
        }

        public Map<String, Endpoint> getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(Map<String, Endpoint> endpoint) {
            this.endpoint = endpoint;
        }


        public FileUriTemplate(UriTemplate uriTemplate) {
           this.templateId = uriTemplate.getTemplateId();
            this.uriTemplate = uriTemplate.getUriTemplate();
            this.authType = uriTemplate.getAuthType();
            this.httpVerb = uriTemplate.getHttpVerb();
            this.policy = uriTemplate.getPolicy().getPolicyName();
        }
    }


}
