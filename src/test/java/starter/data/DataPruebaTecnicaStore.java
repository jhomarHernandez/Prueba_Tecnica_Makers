package starter.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class DataPruebaTecnicaStore {
    private List<DataPruebaTecnica> data = new ArrayList<>();

    public void addData(DataPruebaTecnica pruebaTecnica){
        data.add(pruebaTecnica);
    }

    public void addAllData(Collection<DataPruebaTecnica> allData){
        data.addAll(allData);
    }
}
