package com.application.presentation;

import java.util.ArrayList;
import com.application.timer.Timer;

public class Presentation {
    ArrayList<Slide> slides;
    Slide currentSlide;
    Timer PresentationTimer;

    public Presentation() {
        //Not sure how we want to handle this, depends on how we get data
        this.slides = new ArrayList<>();
    }

    public void addSlide(Slide slide) {
        slides.add(slide);
    }

    public void nextSlide() {
        int nextSlide = slides.indexOf(currentSlide)+1;
        currentSlide = slides.get(nextSlide);
    }

    public void previousSlide() {
        int previousSlide = slides.indexOf(currentSlide)-1;
        currentSlide = slides.get(previousSlide);
    }

    public Slide getCurrentSlide() {
        return currentSlide;
    }

    public int getCurrentSlideIndex() {
        return slides.indexOf(currentSlide)+1;
    }

    public int getTotalSlideCount() {
        return slides.size();
    }

}

