package org.wso2.carbon.apimgt.rest.api.publisher.dto;


import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * StreamDTO
 */
public class StreamDTO   {
  @SerializedName("id")
  private String id = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("version")
  private String version = null;

  @SerializedName("provider")
  private String provider = null;

  @SerializedName("lifeCycleStatus")
  private String lifeCycleStatus = null;

  @SerializedName("streamType")
  private List<String> streamType = new ArrayList<String>();

  @SerializedName("streamAuthorization")
  private List<String> streamAuthorization = new ArrayList<String>();

  public StreamDTO id(String id) {
    this.id = id;
    return this;
  }

   /**
   * UUID of the api registry artifact 
   * @return id
  **/
  @ApiModelProperty(example = "01234567-0123-0123-0123-012345678901", value = "UUID of the api registry artifact ")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public StreamDTO name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "CalculatorAPI", required = true, value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public StreamDTO description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "A calculator API that supports basic operations", value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public StreamDTO version(String version) {
    this.version = version;
    return this;
  }

   /**
   * Get version
   * @return version
  **/
  @ApiModelProperty(example = "1.0.0", required = true, value = "")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public StreamDTO provider(String provider) {
    this.provider = provider;
    return this;
  }

   /**
   * If the provider value is not given user invoking the api will be used as the provider. 
   * @return provider
  **/
  @ApiModelProperty(example = "admin", value = "If the provider value is not given user invoking the api will be used as the provider. ")
  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public StreamDTO lifeCycleStatus(String lifeCycleStatus) {
    this.lifeCycleStatus = lifeCycleStatus;
    return this;
  }

   /**
   * Get lifeCycleStatus
   * @return lifeCycleStatus
  **/
  @ApiModelProperty(example = "CREATED", value = "")
  public String getLifeCycleStatus() {
    return lifeCycleStatus;
  }

  public void setLifeCycleStatus(String lifeCycleStatus) {
    this.lifeCycleStatus = lifeCycleStatus;
  }

  public StreamDTO streamType(List<String> streamType) {
    this.streamType = streamType;
    return this;
  }

  public StreamDTO addStreamTypeItem(String streamTypeItem) {
    this.streamType.add(streamTypeItem);
    return this;
  }

   /**
   * Supported transports for the Stream (http and/or https). 
   * @return streamType
  **/
  @ApiModelProperty(example = "[\"http\",\"https\"]", value = "Supported transports for the Stream (http and/or https). ")
  public List<String> getStreamType() {
    return streamType;
  }

  public void setStreamType(List<String> streamType) {
    this.streamType = streamType;
  }

  public StreamDTO streamAuthorization(List<String> streamAuthorization) {
    this.streamAuthorization = streamAuthorization;
    return this;
  }

  public StreamDTO addStreamAuthorizationItem(String streamAuthorizationItem) {
    this.streamAuthorization.add(streamAuthorizationItem);
    return this;
  }

   /**
   * Supported transports for the Stream (http and/or https). 
   * @return streamAuthorization
  **/
  @ApiModelProperty(example = "[\"BasicAuth\",\"OAuth\"]", value = "Supported transports for the Stream (http and/or https). ")
  public List<String> getStreamAuthorization() {
    return streamAuthorization;
  }

  public void setStreamAuthorization(List<String> streamAuthorization) {
    this.streamAuthorization = streamAuthorization;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StreamDTO stream = (StreamDTO) o;
    return Objects.equals(this.id, stream.id) &&
        Objects.equals(this.name, stream.name) &&
        Objects.equals(this.description, stream.description) &&
        Objects.equals(this.version, stream.version) &&
        Objects.equals(this.provider, stream.provider) &&
        Objects.equals(this.lifeCycleStatus, stream.lifeCycleStatus) &&
        Objects.equals(this.streamType, stream.streamType) &&
        Objects.equals(this.streamAuthorization, stream.streamAuthorization);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, version, provider, lifeCycleStatus, streamType, streamAuthorization);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StreamDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    provider: ").append(toIndentedString(provider)).append("\n");
    sb.append("    lifeCycleStatus: ").append(toIndentedString(lifeCycleStatus)).append("\n");
    sb.append("    streamType: ").append(toIndentedString(streamType)).append("\n");
    sb.append("    streamAuthorization: ").append(toIndentedString(streamAuthorization)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

