package com.example.demo.repository;

import com.example.demo.model.Showtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



import javax.websocket.server.PathParam;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ShowtimeRepo {
    @Autowired
    JdbcTemplate template;

    public List<Showtime> fetchAllShowtimes (){
        String sql = "SELECT*FROM showtime";
        RowMapper<Showtime> rowMapper = new BeanPropertyRowMapper<>(Showtime.class);
        List<Showtime> showtimesFound = template.query(sql, rowMapper);
        return showtimesFound;
    }
    public List<Showtime> fetchAllWithMovieId(int movieId){
        String sql = "SELECT*FROM showtime WHERE movie_id = ?";
        RowMapper<Showtime> rowMapper = new BeanPropertyRowMapper<>(Showtime.class);
        List<Showtime> showtimesForMovie = template.query(sql, rowMapper);
        return showtimesForMovie;
    }
    public Showtime fetchShowtimeWithId(int showtimeId){
        String sql = "SELECT * FROM showtime WHERE showtime.id = ?";
        RowMapper<Showtime> rowMapper = new BeanPropertyRowMapper<>(Showtime.class);
        return template.queryForObject(sql, rowMapper, showtimeId);
    }

    public List<Showtime> fetchAllInWeekWithMovieId (int movieId){
        String sql = "SELECT*FROM showtime WHERE date_time BETWEEN CURRENT_DATE() AND CURRENT_DATE()+6 AND movie_id = ?";
        RowMapper<Showtime> rowMapper = new BeanPropertyRowMapper<>(Showtime.class);
        List<Showtime> movieShowtimesThisWeek = template.query(sql, rowMapper, movieId);
        return movieShowtimesThisWeek;
    }
    public List<Showtime> fetchShowtimesWithDateAndMovieId (LocalDate date, int movieId){
        String sql = "SELECT * FROM showtime WHERE movie_id = ? and DATEDIFF(date_time, ?) = 0";
        RowMapper<Showtime> rowMapper = new BeanPropertyRowMapper<>(Showtime.class);
        List<Showtime> showtimes = template.query(sql, rowMapper, movieId, date);
        return showtimes;
    }
    public boolean addShowtime(Showtime showtime){
        String sql = "INSERT INTO showtime (movie_id, room_id, date_time) VALUES (?, ?, ?)";
        return template.update(sql, showtime.getMovieId(), showtime.getRoomId(), showtime.getDateTime()) > 0;
    }

    public boolean deleteShowtime(int showtimeId){
        String sql = "DELETE FROM showtime WHERE showtime.id = ?";
        return template.update(sql, showtimeId) < 0;
    }



    // ---------------------------- Take away and insert the one Ithai made and wants to use. ----------------------------
    public int fetchRoomIdFromShowtime (int showtimeId){
        String sql = "SELECT showtime.room_id FROM showtime WHERE id = ?";
        RowMapper<Integer> rowMapper = new BeanPropertyRowMapper<>(Integer.class);
        List<Integer> roomIds = template.query(sql, rowMapper);
        return roomIds.get(0);
    }
}