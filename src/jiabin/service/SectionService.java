package jiabin.service;

import java.util.List;

import jiabin.entity.PageBean;
import jiabin.entity.Section;

public interface SectionService {

	public void saveSection(Section section);
	
	public void deleteSection(Section section);
	
	public List<Section> findSectionList(Section s_section,PageBean pageBean);
	
	public Long getSectionCount(Section s_section);
	
	public Section findSectionById(int sectionId);
	
}
