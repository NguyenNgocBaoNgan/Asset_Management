package DACNPM.asset_management.service;

import DACNPM.asset_management.model.Status;
import DACNPM.asset_management.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;


}
