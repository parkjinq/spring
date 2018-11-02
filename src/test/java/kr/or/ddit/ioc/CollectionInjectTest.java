package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:kr/or/ddit/ioc/application_context_collection.xml"})
public class CollectionInjectTest {

	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	/**
	 * 스프링 설정파일을 통해서 collection객체 주입이 정상적으로 이루어지는지 테스트
	 */
	@Test
	public void collectionBeanTest() {
		/***Given***/
		
		/***When***/
		List<String> list = collectionBean.getList();
		Set<String> set = collectionBean.getSet();
		Map<String, String> map =collectionBean.getMap();
		Properties property = collectionBean.getProperty();
		
		/***Then***/
		assertNotNull(collectionBean); //spring 컨데이터에서 DL을 통해 받은 빈 : 정상적이라면 null일 수 없다.
		
		//list검증
		assertEquals(3, list.size());
		assertEquals("brown_list", list.get(0));
		
		//set검증
		assertEquals(3, set.size());
		
		//map검증
		assertEquals("brown_map", map.get("name"));
		assertEquals("28_map", map.get("age"));
		assertEquals("male_map", map.get("gender"));
		
		//properties검증
		assertEquals("jin", property.get("db.userId"));
		assertEquals("java", property.get("db.password"));
	}

}
