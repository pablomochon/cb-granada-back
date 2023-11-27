package com.basketballticketsproject.basketballticketsproject.Excel;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@ExcelSheet("FechasPartido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FechaPartido {

    @ExcelCellName("FECHA")
    private String fecha;

}