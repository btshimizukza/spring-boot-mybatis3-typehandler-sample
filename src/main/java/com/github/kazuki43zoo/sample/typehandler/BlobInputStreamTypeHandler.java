package com.github.kazuki43zoo.sample.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.io.InputStream;
import java.sql.*;

// (1)
public class BlobInputStreamTypeHandler extends BaseTypeHandler<InputStream> {

    // (2)
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, InputStream parameter,
                                    JdbcType jdbcType) throws SQLException {
        ps.setBlob(i, parameter);
    }

    // (3)
    @Override
    public InputStream getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return toInputStream(rs.getBlob(columnName));
    }

    // (3)
    @Override
    public InputStream getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return toInputStream(rs.getBlob(columnIndex));
    }

    // (3)
    @Override
    public InputStream getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return toInputStream(cs.getBlob(columnIndex));
    }

    private InputStream toInputStream(Blob blob) throws SQLException {
        // (4)
        if (blob == null) {
            return null;
        } else {
            return blob.getBinaryStream();
        }
    }

}