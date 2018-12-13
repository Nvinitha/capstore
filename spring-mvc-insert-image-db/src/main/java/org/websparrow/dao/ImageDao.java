package org.websparrow.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.multipart.MultipartFile;
import org.websparrow.model.ImageBean;

public class ImageDao {

	private JdbcTemplate jdbcTemplate;

	public ImageDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int inserRecords(String name, Integer age, MultipartFile photo) throws IOException {

		byte[] photoBytes = photo.getBytes();

		String sql = "INSERT INTO EXAMPLE(NAME,AGE,PHOTO) VALUES (?,?,?)";

		return jdbcTemplate.update(sql, new Object[] { name, age, photoBytes });
	}
	
	
	public List<ImageBean> stuList() {

		List<ImageBean> list = jdbcTemplate.query("SELECT * FROM student", new RowMapper<ImageBean>() {
	public ImageBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ImageBean img = new ImageBean();

		img.setId(rs.getInt("id"));
		img.setName(rs.getString("name"));
		img.setAge(rs.getInt("age"));

		return img;
	}
		});

		return list;
	}
	
	
	public Blob getPhotoById(int id) {

		String query = "select photo from example where id=?";

		Blob photo = jdbcTemplate.queryForObject(query, new Object[] { id }, Blob.class);

		return photo;
	}
}
