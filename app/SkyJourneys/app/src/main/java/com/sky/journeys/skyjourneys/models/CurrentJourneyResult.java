package com.sky.journeys.skyjourneys.models;

public class CurrentJourneyResult {
    private String id;
    private String from;
    private String to;
    private String outboundDate;
    private String inboundDate;
    private String image;

    public CurrentJourneyResult(String id, String from , String to, String outboundDate, String inboundDate, String image) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.outboundDate = outboundDate;
        this.inboundDate = inboundDate;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getInboundDate() {
        return inboundDate;
    }

    public String getOutboundDate() {
        return outboundDate;
    }

    public String getImage() {
        return image;
    }
}
