package DACNPM.asset_management.service;

import DACNPM.asset_management.model.Type;
import DACNPM.asset_management.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    TypeRepository typeRepository;


}
