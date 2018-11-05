package net.le.baseframe.core.dao;

import java.util.List;
import java.util.Map;

public interface ReportDao {
    List<Map<String, Object>> getUserReport();
    List<Map<String, Object>> getLoanTypeMoney();
    List<Map<String, Object>> getNoPayReport();
}
