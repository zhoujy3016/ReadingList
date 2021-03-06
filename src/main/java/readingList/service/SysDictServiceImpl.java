/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package readingList.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import readingList.domain.SysDictEntity;
import readingList.mapper.SysDictMapper;
import readingList.repository.SysDictRepository;

import java.util.List;




@Service("iSysDictService")
public class SysDictServiceImpl implements ISysDictService {

	@Autowired
	private SysDictRepository sysDictRepository; 
	
	@Autowired
	private SysDictMapper sysDictMapper;

	@Override
	@Cacheable(cacheNames="dictionary", key="#type")
	public List<SysDictEntity> getSysDictEntity(String type) {
		return this.sysDictRepository.findByType(type);
	}

	@Override
	public List<SysDictEntity> getSysDictEntityGroupByType() {
		return sysDictMapper.findAllGroupByType();
	}

}
