package com.github.kazuki43zoo.sample.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;

@MappedJdbcTypes(JdbcType.BLOB)
public class BlobStringTypeHandler extends BaseTypeHandler<String> {

    // (2)
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter,
                                    JdbcType jdbcType) throws SQLException {
        ps.setBlob(i, new ByteArrayInputStream(parameter.getBytes(StandardCharsets.UTF_8)));
    }

    // (3)
    @Override
    public String getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return toString(rs.getBlob(columnName));
    }

    // (3)
    @Override
    public String getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return toString(rs.getBlob(columnIndex));
    }

    // (3)
    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return toString(cs.getBlob(columnIndex));
    }

    private String toString(Blob blob) throws SQLException {
        // (4)
        if (blob == null) {
            return null;
        } else {
            try {
                return StreamUtils.copyToString(blob.getBinaryStream(),StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}