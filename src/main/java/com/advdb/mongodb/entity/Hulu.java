package com.advdb.mongodb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.util.Arrays;
import java.util.Date;

@Document(collection = "Hulu")
public class Hulu {

    @Id
    @JsonIgnore
    public ObjectId _id;

    @NotNull(message = "Id Cannot be Null")
    @Field("id")
    private Integer id;

    @NotNull(message = "Title Cannot be Null")
    private String title;

    private Integer clips_count;
    private String description;
    private Integer episodes_count;
    private String[] genres;
    private Double score;
    private Integer seasons_count;
    private String company;
    private Date released_at;
    private String rating;

    public Hulu() {
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Hulu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", clips_count=" + clips_count +
                ", description='" + description + '\'' +
                ", episodes_count=" + episodes_count +
                ", genres=" + Arrays.toString(genres) +
                ", score=" + score +
                ", seasons_count=" + seasons_count +
                ", company='" + company + '\'' +
                ", released_at=" + released_at +
                ", rating='" + rating + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getClips_count() {
        return clips_count;
    }

    public void setClips_count(Integer clips_count) {
        this.clips_count = clips_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(Integer episodes_count) {
        this.episodes_count = episodes_count;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(Integer seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getReleased_at() {
        return released_at;
    }

    public void setReleased_at(Date released_at) {
        this.released_at = released_at;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Hulu(Integer id, String title, Integer clips_count, String description, Integer episodes_count, String[] genres, Double score, Integer seasons_count, String company, Date released_at, String rating) {
        this.id = id;
        this.title = title;
        this.clips_count = clips_count;
        this.description = description;
        this.episodes_count = episodes_count;
        this.genres = genres;
        this.score = score;
        this.seasons_count = seasons_count;
        this.company = company;
        this.released_at = released_at;
        this.rating = rating;
    }
}
