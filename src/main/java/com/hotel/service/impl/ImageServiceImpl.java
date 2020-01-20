package com.hotel.service.impl;

import com.hotel.model.Image;
import com.hotel.model.TypeRoom;
import com.hotel.repository.ImageRepository;
import com.hotel.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;

public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepository;
    @Override
    public Iterable<Image> findAll() {
        return imageRepository.findAll();
    }

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public void remove(Long id) {
        imageRepository.deleteById(id);
    }

    @Override
    public Iterable<Image> findImagesByTypeRoom(TypeRoom typeRoom) {
        return imageRepository.findImagesByTypeRoom(typeRoom);
    }
}
