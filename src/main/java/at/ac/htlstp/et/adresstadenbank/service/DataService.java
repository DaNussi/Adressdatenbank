package at.ac.htlstp.et.adresstadenbank.service;


import at.ac.htlstp.et.adresstadenbank.dto.LandDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataService {

    @Autowired private JdbcTemplate jdbcTemplate;

    public List<LandDto> tables(){
        List<LandDto> tables=new ArrayList<>();
        jdbcTemplate.execute("use eulaender");

        for (Map<String,Object> row : jdbcTemplate.queryForList("select * from laender")) {
            tables.add(
                    new LandDto(
                            Integer.parseInt(row.get("id").toString()),
                            row.get("Name").toString(),
                            row.get("Hauptstadt").toString(),
                            row.get("Einwohner").toString()
                    )
            );
        }
        return tables;
    }


    public void speicherLand(LandDto landDto) {
        jdbcTemplate.execute("use eulaender");

        try {
            jdbcTemplate.execute("DELETE FROM laender WHERE id= " + landDto.getId());
        } catch (Exception e) {

        }

        if(landDto.getId() <= 0) {
            int index = 1;

            for(LandDto l : tables()) {
                if(l.getId() != index) {
                    landDto.setId(index - 1);
                    break;
                }
                index++;
            }
            landDto.setId(index);
        }

        jdbcTemplate.execute("insert into laender (id, Name, Hauptstadt, Einwohner) " +
                "values ('"+landDto.getId()+"'," +
                "'"+landDto.getName()+"'," +
                "'"+landDto.getHauptstadt()+"'," +
                "'"+landDto.getEinwohner()+"')");


    }

    public void loescheLand (int id) {
        jdbcTemplate.execute("use eulaender");

        jdbcTemplate.execute("DELETE FROM laender WHERE id= " + id);

    }


}


/*
Im Service programmieren Datenbanken bearbeiten, löschen, hinzufügen
 */
