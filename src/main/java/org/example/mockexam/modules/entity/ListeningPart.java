package org.example.mockexam.modules.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

@Entity
public class ListeningPart extends BaseEntity {
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String audioUrl;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.LAZY)
    private ListeningTest listeningTest;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ListeningTest getListeningTest() {
        return listeningTest;
    }

    public void setListeningTest(ListeningTest listeningTest) {
        this.listeningTest = listeningTest;
    }
}
