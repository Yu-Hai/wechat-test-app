package com.office.wechat.message;
import com.office.common.annotation.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;
  
public class MediaIdMessage {  
    @XStreamAlias("MediaId")  
    @XStreamCDATA  
    private String MediaId;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
}  