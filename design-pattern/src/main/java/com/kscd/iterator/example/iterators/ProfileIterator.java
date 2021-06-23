package com.kscd.iterator.example.iterators;

import com.kscd.iterator.example.profile.Profile;

public interface ProfileIterator {
    boolean hasNext();

    Profile getNext();

    void reset();
}
