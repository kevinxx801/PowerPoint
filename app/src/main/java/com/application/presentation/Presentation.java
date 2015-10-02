package com.application.presentation;

import java.util.ArrayList;

public class Presentation {
    ArrayList<Slide> slides;
    Slide currentSlide;

    public Presentation() {
        //Not sure how we want to handle this, depends on how we get data
        this.slides = new ArrayList<>();
    }

    public void addSlide(Slide slide) {
        slides.add(slide);
    }

    public void nextSlide() {
        int nextSlide = slides.indexOf(this.currentSlide)+1;
        this.currentSlide = slides.get(nextSlide);
    }

    public void previousSlide() {
        int previousSlide = slides.indexOf(this.currentSlide)-1;
        this.currentSlide = slides.get(previousSlide);
    }

    public int getCurrentSlideIndex() {
        int currentSlideIndex = slides.indexOf(this.currentSlide)+1;
        return currentSlideIndex;
    }

    public int getTotalSlideCount() {
        return slides.size();
    }

}

