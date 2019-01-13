package com.example.springsocial;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL9Dialect;
import org.hibernate.type.descriptor.sql.ClobTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class CustomPDial extends PostgreSQL9Dialect {
@Override
public SqlTypeDescriptor getSqlTypeDescriptorOverride(int sqlCode) {
	if(sqlCode == Types.CLOB){
	     return ClobTypeDescriptor.CLOB_BINDING;
	    }
	    return super.getSqlTypeDescriptorOverride( sqlCode );
}
}
