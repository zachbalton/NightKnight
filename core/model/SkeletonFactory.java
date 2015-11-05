package com.skylarkingstudios.nightknight.objects;


import java.util.Random;

public class SkeletonFactory {

    private int d = 0;
    Random rand = new Random();

    public static SkeletonFactory SF = new SkeletonFactory();

    public Skeleton makeSkeleton() {
        rand = new Random();
        d = rand.nextInt(2);
        if (d == 0) {
            return new Skeleton("Left");
        }
        else
            return new Skeleton("Right");


    }

}
