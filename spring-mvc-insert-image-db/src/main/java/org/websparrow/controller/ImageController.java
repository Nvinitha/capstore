package org.websparrow.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.websparrow.dao.ImageDao;
import org.websparrow.model.ImageBean;

import com.mysql.cj.jdbc.Blob;

@Controller
public class ImageController {

	@Autowired
	ImageDao imageDao;

	@RequestMapping(value = "/InsertImage", method = RequestMethod.POST)
	public ModelAndView save(@RequestParam("name") String name, @RequestParam("age") Integer age,
			@RequestParam("photo") MultipartFile photo) {

		try {
			imageDao.inserRecords(name, age, photo);

			return new ModelAndView("index", "msg", "Records succesfully inserted into database.");

		} catch (Exception e) {
			return new ModelAndView("index", "msg", "Error: " + e.getMessage());
		}
	}
	
	@RequestMapping(value = "/fetch")
	public ModelAndView listStudent(ModelAndView model) throws IOException {

		List<ImageBean> listStu = imageDao.stuList();

		model.addObject("listStu", listStu);
		model.setViewName("index");

		return model;
	}
	
	@RequestMapping(value = "/getStudentPhoto/{id}")
	public void getStudentPhoto(HttpServletResponse response, @PathVariable("id") int id) throws Exception {
		response.setContentType("image/jpeg");

		Blob ph = (Blob) imageDao.getPhotoById(id);

		byte[] bytes = ph.getBytes(1, (int) ph.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
}
