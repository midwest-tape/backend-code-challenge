package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.Address;
import com.midwesttape.project.challengeapplication.model.User;
import lombok.RequiredArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final JdbcTemplate template;

//    public User user(final Long userId) {
//        try {
//
//            return template.queryForObject(
//                "select " +
//                    "id, " +
//                    "firstName, " +
//                    "lastName, " +
//                    "username, " +
//                    "password " +
//                    "from User " +
//                    "where id = ?",
//                new BeanPropertyRowMapper<>(User.class),
//                userId
//            );
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//
//    }

	public User user(final Long userId) {
		try {
			String query = "select * from User where id = ?";
			return template.queryForObject(query, 
					new UserMapper(new AddressMapper()), 
					userId);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	private static final class UserMapper implements RowMapper<User> {

		private final AddressMapper addressMapper;

		public UserMapper(AddressMapper addressMapper) {
			this.addressMapper = addressMapper;
		}

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setFirstName(rs.getString("firstName"));
			user.setLastName(rs.getString("lastName"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));

			Address address = this.addressMapper.mapRow(rs, rowNum);
			user.setAddressId(address);

			return user;
		}
	}

	public class AddressMapper implements RowMapper<Address> {

		@Override
		public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
			return template.queryForObject("Select * from Address where id = ?", 
					new BeanPropertyRowMapper<>(Address.class), 
					rs.getLong("id"));
		}
	}
	
	public Integer save(User user) {
		return template.update("update user set firstName = ?,"
				+ " lastName = ?,"
				+ " username = ?,"
				+ " password = ? "
				+ "where id = ?", user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getId());
	}

}
