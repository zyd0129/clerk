package com.wind.clerk.oauth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@ConfigurationProperties("encrypt")
public class KeyProperties {

    /**
     * A symmetric key. As a stronger alternative, consider using a keystore.
     */
    private String key;

    /**
     * A salt for the symmetric key, in the form of a hex-encoded byte array. As a
     * stronger alternative, consider using a keystore.
     */
    private String salt = "deadbeef";

    /**
     * Flag to say that a process should fail if there is an encryption or decryption
     * error.
     */
    private boolean failOnError = true;

    /**
     * The key store properties for locating a key in a Java Key Store (a file in a format
     * defined and understood by the JVM).
     */
    private org.springframework.cloud.bootstrap.encrypt.KeyProperties.KeyStore keyStore = new org.springframework.cloud.bootstrap.encrypt.KeyProperties.KeyStore();

    public boolean isFailOnError() {
        return this.failOnError;
    }

    public void setFailOnError(boolean failOnError) {
        this.failOnError = failOnError;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public org.springframework.cloud.bootstrap.encrypt.KeyProperties.KeyStore getKeyStore() {
        return this.keyStore;
    }

    public void setKeyStore(org.springframework.cloud.bootstrap.encrypt.KeyProperties.KeyStore keyStore) {
        this.keyStore = keyStore;
    }

    /**
     * Key store properties.
     */
    public static class KeyStore {

        /**
         * Location of the key store file, e.g. classpath:/keystore.jks.
         */
        private Resource location;

        /**
         * Password that locks the keystore.
         */
        private String password;

        /**
         * Alias for a key in the store.
         */
        private String alias;

        /**
         * Secret protecting the key (defaults to the same as the password).
         */
        private String secret;

        /**
         * The KeyStore type. Defaults to jks.
         */
        private String type = "jks";

        public String getAlias() {
            return this.alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public Resource getLocation() {
            return this.location;
        }

        public void setLocation(Resource location) {
            this.location = location;
        }

        public String getPassword() {
            return this.password;
        }

        public String getType() {
            return type;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSecret() {
            return this.secret == null ? this.password : this.secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

}