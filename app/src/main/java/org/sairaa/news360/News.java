package org.sairaa.news360;

public class News {
    private String id,
            type,
            sectionId,
            sectionName,
            webPublicationDate,
            webTitle,
            webUrl,
            apiUrl,
            fieldThumbnail,
            tagContributor,
            isHosted,
            pillarId,
            pillarName;


    public News(String id, String type, String sectionId, String sectionName,
                String webPublicationDate, String webTitle, String webUrl, String apiUrl,
                String fieldThumbnail,
                String tagContributor,
                String isHosted, String pillarId, String pillarName){

        this.id = id;
        this.type = type;
        this.sectionId = sectionId;
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.webTitle = webTitle;
        this.webUrl = webUrl;
        this.apiUrl = apiUrl;
        this.fieldThumbnail = fieldThumbnail;
        this.tagContributor = tagContributor;
        this.isHosted = isHosted;
        this.pillarId = pillarId;
        this.pillarName = pillarName;

    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSectionId() {
        return sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    public String getWebTitle() {
        return webTitle;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getFieldThumbnail() {
        return fieldThumbnail;
    }

    public String getTagContributor() {
        return tagContributor;
    }

    public String getIsHosted() {
        return isHosted;
    }

    public String getPillarId() {
        return pillarId;
    }

    public String getPillarName() {
        return pillarName;
    }

}
