package com.mycompany.propertymanagement.Service.impl;

import com.mycompany.propertymanagement.Service.PropertyService;
import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private PropertyConverter propertyConverter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = propertyConverter.convertDTOtoEntity(propertyDTO);

//        PropertyEntity pe= new PropertyEntity();
//        pe.setTitle(propertyDTO.getTitle());
//        pe.setAddress(propertyDTO.getAddress());
//        pe.setOwnerEmail(propertyDTO.getOwnerEmail());
//        pe.setOwnerName(propertyDTO.getOwnerName());
//        pe.setPrice(propertyDTO.getPrice());
//        pe.setDescription(propertyDTO.getDescription());

        pe = propertyRepository.save(pe);
        propertyDTO = propertyConverter.convertEntityTODTO(pe);

        return propertyDTO;
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        List<PropertyEntity> listofProps = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propList = new ArrayList<>();
        for (PropertyEntity pe : listofProps){
            PropertyDTO dto = propertyConverter.convertEntityTODTO(pe);
            propList.add(dto);
        }
        return propList;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optEn.isPresent()) {
            PropertyEntity pe = optEn.get(); //data from database
            pe.setTitle(propertyDTO.getTitle()); // Overriding values
            pe.setAddress(propertyDTO.getAddress());
            pe.setPrice(propertyDTO.getPrice());
            pe.setDescription(propertyDTO.getDescription());
            dto = propertyConverter.convertEntityTODTO(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optEn.isPresent()) {
            PropertyEntity pe = optEn.get(); //data from database
            pe.setDescription(propertyDTO.getDescription()); //Overriting data
            dto = propertyConverter.convertEntityTODTO(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optEn.isPresent()) {
            PropertyEntity pe = optEn.get(); //data from database
            pe.setPrice(propertyDTO.getPrice());
            dto = propertyConverter.convertEntityTODTO(pe);
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }

}

