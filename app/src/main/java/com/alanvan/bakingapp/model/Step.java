package com.alanvan.bakingapp.model;

import com.alanvan.bakingapp.db.cache.CachableEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = Step.TABLE_NAME)
public class Step implements CachableEntity {

    public static final String TABLE_NAME = "Step";
    public static final String COLUMN_RECIPE_ID = "recipe_id";
    public static final String COLUMN_STEP_ID = "step_id";
    public static final String COLUMN_STEP_DB_ID = "step_db_id";
    public static final String COLUMN_SHORT_DESC = "short_desc";
    public static final String COLUMN_DESCRIPTION = "desc";
    public static final String COLUMN_VIDEO_URL = "videoURL";
    public static final String COLUMN_THUMBNAIL_URL = "thumbnailURL";

    @DatabaseField(columnName = COLUMN_STEP_ID)
    @SerializedName("id")
    @Expose
    private Integer id;

    @DatabaseField(columnName = COLUMN_STEP_DB_ID, id = true)
    private String stepDbId;

    @DatabaseField(columnName = COLUMN_RECIPE_ID)
    private int recipeId;

    @DatabaseField(columnName = COLUMN_SHORT_DESC)
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;

    @DatabaseField(columnName = COLUMN_DESCRIPTION)
    @SerializedName("description")
    @Expose
    private String description;

    @DatabaseField(columnName = COLUMN_VIDEO_URL)
    @SerializedName("videoURL")
    @Expose
    private String videoURL;

    @DatabaseField(columnName = COLUMN_THUMBNAIL_URL)
    @SerializedName("thumbnailURL")
    @Expose
    private String thumbnailURL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getShortDescription() {
        return shortDescription;
    }


    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public String getStepDbId() {
        return stepDbId;
    }

    public void setStepDbId(String stepDbId) {
        this.stepDbId = stepDbId;
    }
}
