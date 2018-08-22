package readingList.service;

import readingList.domain.Publish;

import java.util.List;

public interface IPublishService {

    List<Publish> findPublishList(String type);

}
