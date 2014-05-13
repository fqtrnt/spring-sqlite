/**
 *
 * Copyright 2014 VIMICRO. All rights reserved.
 */
package org.study;

import static com.google.common.collect.Lists.newArrayList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.study.entity.Subject;


/**
 * @author fqtrnt [2014年4月23日]
 * @since 1.0.0.0
 */
@Controller
@RequestMapping(produces = "application/json", method = { POST, GET } )
public class CardController {
	@Autowired
	protected CardService carService;
	@RequestMapping(value = "")
	public String index() {
		return "redirect:/index.htm";
	}
	@ResponseBody
	@RequestMapping(value = "subjects")
	public List<Subject> subjects() {
		return newArrayList(carService.loadAllSubjects());
	}
	
	@ResponseBody
	@RequestMapping(value = "subject/save")
	public int saveSubject(@RequestBody Subject subject) {
		return carService.save(subject);
	}
}
