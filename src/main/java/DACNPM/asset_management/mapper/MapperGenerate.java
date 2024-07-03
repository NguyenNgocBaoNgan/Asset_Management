package DACNPM.asset_management.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class MapperGenerate {
    @Autowired
    private ModelMapper modelMapper;

    public <E, D> E convert(D obj, Class<E> objClass) {
        return modelMapper.map(obj, objClass);
    }

    public <E, D> List<D> toListConvert(List<E> list, Class<D> objClass) {
        List<D> result = new ArrayList<>();
        for (E e : list) {
            result.add(convert(e, objClass));
        }
        return result;
    }
}
