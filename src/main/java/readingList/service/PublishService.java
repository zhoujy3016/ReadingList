package readingList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import readingList.domain.Publish;
import readingList.mapper.PublishMapper;

import java.util.List;

@Service
public class PublishService implements IPublishService{

    @Autowired
    private PublishMapper publishMapper;

    @Override
    @Cacheable(cacheNames="extra_dictionary", key="#type")
    public List<Publish> findPublishList(String type) {
        return publishMapper.findPublishList();
    }
}
