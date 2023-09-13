package com.smartcontactmanager.service;

import com.smartcontactmanager.entities.Contact;
import com.smartcontactmanager.entities.User;
import com.smartcontactmanager.repository.ContactRepository;
import com.smartcontactmanager.repository.UserRepository;
import com.smartcontactmanager.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private FileUtils fileUtils;


    public boolean addContact(String userName, Contact contact, MultipartFile profileImage) {
        try {
            User user = userRepository.findUserByFirstName(userName);
            contact.setUser(user);
            user.getContacts().add(contact);
            if (!profileImage.isEmpty()) {
                contact.setProfileImg(profileImage.getOriginalFilename());
                fileUtils.saveImage(profileImage);
            }
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Page<Contact> findPaginatedContactsList(User user, int pageNo, int pageSize, String sortField, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                    Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return contactRepository.findContactsByUser(user, pageable);
    }

    public Optional<Contact> getContactById(int id){
        return contactRepository.findById(id);
    }

    public void deleteContactById(int id){
        contactRepository.deleteById(id);
    }

    public List<Contact> findContactContainingName(String name, User user){
        return contactRepository.findByNameContainingIgnoreCaseAndUser(name, user);
    }

}
