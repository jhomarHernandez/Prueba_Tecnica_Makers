package starter.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class DataPruebaTecnica {
    private String fecha;
    private String portafolio;
    private int nominal;
    private double precio;
    private int total;
}
