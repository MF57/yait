package edu.agh.yait.userData;

import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import java.util.Optional;

/**
 * Created by mf57 on 07.05.2017.
 */
public class UserDataAttributesMapper implements AttributesMapper<UserData> {
    @Override
    public UserData mapFromAttributes(Attributes attributes) throws NamingException {

        Attribute ipaUniqueID = attributes.get("ipaUniqueID");
        Optional<Attribute> givenName = Optional.ofNullable(attributes.get("givenName"));
        Optional<Attribute> sn = Optional.ofNullable(attributes.get("sn"));
        Optional<Attribute> mail = Optional.ofNullable(attributes.get("mail"));
        return new UserData(
                (String) ipaUniqueID.get(),
                givenName.map(name -> {
                    try {
                        return (String) name.get();
                    } catch (NamingException e) {
                        return "";
                    }
                }),
                sn.map(surname -> {
                    try {
                        return (String) surname.get();
                    } catch (NamingException e) {
                        return "";
                    }
                }),
                mail.map(email -> {
                    try {
                        return (String) email.get();
                    } catch (NamingException e) {
                        return "";
                    }
                })
        );
    }
}
