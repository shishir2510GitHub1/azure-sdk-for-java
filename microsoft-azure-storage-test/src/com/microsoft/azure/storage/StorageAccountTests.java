/**
 * Copyright Microsoft Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.azure.storage;

import static org.junit.Assert.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Locale;
import java.util.UUID;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.microsoft.azure.storage.TestRunners.CloudTests;
import com.microsoft.azure.storage.TestRunners.DevFabricTests;
import com.microsoft.azure.storage.TestRunners.DevStoreTests;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.core.Base64;
import com.microsoft.azure.storage.core.SR;
import com.microsoft.azure.storage.file.CloudFileClient;
import com.microsoft.azure.storage.file.CloudFileShare;
import com.microsoft.azure.storage.queue.CloudQueue;
import com.microsoft.azure.storage.queue.CloudQueueClient;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;

@Category({ DevFabricTests.class, DevStoreTests.class, CloudTests.class })
public class StorageAccountTests {

    public static String accountName = UUID.randomUUID().toString();
    public static String accountKey = Base64.encode(UUID.randomUUID().toString().getBytes());

    @Test
    public void testStorageCredentialsAnonymous() throws URISyntaxException, StorageException {
        StorageCredentialsAnonymous cred = new StorageCredentialsAnonymous();

        assertNull(cred.getAccountName());

        URI testUri = new URI("http://test/abc?querya=1");
        assertEquals(testUri, cred.transformUri(testUri));
    }

    @Test
    public void testStorageCredentialsSharedKey() throws URISyntaxException, StorageException {
        StorageCredentialsAccountAndKey cred = new StorageCredentialsAccountAndKey(accountName, accountKey);

        assertEquals(accountName, cred.getAccountName());

        URI testUri = new URI("http://test/abc?querya=1");
        assertEquals(testUri, cred.transformUri(testUri));

        assertEquals(accountKey, cred.getCredentials().exportBase64EncodedKey());
        byte[] dummyKey = { 0, 1, 2 };
        String base64EncodedDummyKey = Base64.encode(dummyKey);
        cred = new StorageCredentialsAccountAndKey(accountName, base64EncodedDummyKey);
        assertEquals(base64EncodedDummyKey, cred.getCredentials().exportBase64EncodedKey());

        dummyKey[0] = 3;
        base64EncodedDummyKey = Base64.encode(dummyKey);
        cred = new StorageCredentialsAccountAndKey(accountName, base64EncodedDummyKey);
        assertEquals(base64EncodedDummyKey, cred.getCredentials().exportBase64EncodedKey());
    }

    @Test
    public void testStorageCredentialsSAS() throws URISyntaxException, StorageException {
        String token = "?sp=abcde&api-version=2014-02-14&sig=1";

        StorageCredentialsSharedAccessSignature cred = new StorageCredentialsSharedAccessSignature(token);

        assertNull(cred.getAccountName());

        URI testUri = new URI("http://test/abc");
        assertEquals(testUri + token, cred.transformUri(testUri).toString());

        testUri = new URI("http://test/abc?query=a&query2=b");
        String expectedUri = "http://test/abc?sp=abcde&query=a&api-version=2014-02-14&query2=b&sig=1";
        assertEquals(expectedUri, cred.transformUri(testUri).toString());
    }

    @Test
    public void testStorageCredentialsEmptyKeyValue() throws URISyntaxException, InvalidKeyException {
        String emptyKeyValueAsString = "";
        String emptyKeyConnectionString = String.format(Locale.US,
                "DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=", accountName);

        StorageCredentialsAccountAndKey credentials1 = new StorageCredentialsAccountAndKey(accountName,
                emptyKeyValueAsString);
        assertEquals(accountName, credentials1.getAccountName());
        assertEquals(emptyKeyValueAsString, Base64.encode(credentials1.getCredentials().exportKey()));

        CloudStorageAccount account1 = new CloudStorageAccount(credentials1, true);
        assertEquals(emptyKeyConnectionString, account1.toString(true));
        assertNotNull(account1.getCredentials());
        assertEquals(accountName, account1.getCredentials().getAccountName());
        assertEquals(emptyKeyValueAsString,
                Base64.encode(((StorageCredentialsAccountAndKey) (account1.getCredentials())).getCredentials()
                        .exportKey()));

        CloudStorageAccount account2 = CloudStorageAccount.parse(emptyKeyConnectionString);
        assertEquals(emptyKeyConnectionString, account2.toString(true));
        assertNotNull(account2.getCredentials());
        assertEquals(accountName, account2.getCredentials().getAccountName());
        assertEquals(emptyKeyValueAsString,
                Base64.encode(((StorageCredentialsAccountAndKey) (account2.getCredentials())).getCredentials()
                        .exportKey()));

        StorageCredentialsAccountAndKey credentials2 = new StorageCredentialsAccountAndKey(accountName, accountKey);
        assertEquals(accountName, credentials2.getAccountName());
        assertEquals(accountKey, Base64.encode(credentials2.getCredentials().exportKey()));

        byte[] emptyKeyValueAsByteArray = new byte[0];
        StorageCredentialsAccountAndKey credentials3 = new StorageCredentialsAccountAndKey(accountName,
                emptyKeyValueAsByteArray);
        assertEquals(accountName, credentials3.getAccountName());
        assertEquals(Base64.encode(emptyKeyValueAsByteArray), Base64.encode(credentials3.getCredentials().exportKey()));
    }

    @Test
    public void testStorageCredentialsNullKeyValue() {
        String nullKeyValueAsString = null;

        try {
            new StorageCredentialsAccountAndKey(accountName, nullKeyValueAsString);
            fail("Did not hit expected exception");
        }
        catch (NullPointerException ex) {
            //            assertEquals(SR.KEY_NULL, ex.getMessage());
        }

        StorageCredentialsAccountAndKey credentials2 = new StorageCredentialsAccountAndKey(accountName, accountKey);
        assertEquals(accountName, credentials2.getAccountName());
        assertEquals(accountKey, Base64.encode(credentials2.getCredentials().exportKey()));

        byte[] nullKeyValueAsByteArray = null;
        try {
            new StorageCredentialsAccountAndKey(accountName, nullKeyValueAsByteArray);
            fail("Did not hit expected exception");
        }
        catch (IllegalArgumentException ex) {
            assertEquals(SR.KEY_NULL, ex.getMessage());
        }
    }

    private void AccountsAreEqual(CloudStorageAccount a, CloudStorageAccount b) {
        // endpoints are the same
        assertEquals(a.getBlobEndpoint(), b.getBlobEndpoint());
        assertEquals(a.getQueueEndpoint(), b.getQueueEndpoint());
        assertEquals(a.getTableEndpoint(), b.getTableEndpoint());
        assertEquals(a.getFileEndpoint(), b.getFileEndpoint());

        // storage uris are the same
        assertEquals(a.getBlobStorageUri(), b.getBlobStorageUri());
        assertEquals(a.getQueueStorageUri(), b.getQueueStorageUri());
        assertEquals(a.getTableStorageUri(), b.getTableStorageUri());
        assertEquals(a.getFileStorageUri(), b.getFileStorageUri());

        // seralized representatons are the same.
        String aToStringNoSecrets = a.toString();
        String aToStringWithSecrets = a.toString(true);
        String bToStringNoSecrets = b.toString(false);
        String bToStringWithSecrets = b.toString(true);
        assertEquals(aToStringNoSecrets, bToStringNoSecrets);
        assertEquals(aToStringWithSecrets, bToStringWithSecrets);

        // credentials are the same
        if (a.getCredentials() != null && b.getCredentials() != null) {
            assertEquals(a.getCredentials().getClass(), b.getCredentials().getClass());
        }
        else if (a.getCredentials() == null && b.getCredentials() == null) {
            return;
        }
        else {
            fail("credentials mismatch");
        }
    }

    @Test
    public void testCloudStorageAccountDevelopmentStorageAccount() throws InvalidKeyException, URISyntaxException {
        CloudStorageAccount devstoreAccount = CloudStorageAccount.getDevelopmentStorageAccount();
        assertEquals(devstoreAccount.getBlobStorageUri().getPrimaryUri(), new URI(
                "http://127.0.0.1:10000/devstoreaccount1"));
        assertEquals(devstoreAccount.getQueueStorageUri().getPrimaryUri(), new URI(
                "http://127.0.0.1:10001/devstoreaccount1"));
        assertEquals(devstoreAccount.getTableStorageUri().getPrimaryUri(), new URI(
                "http://127.0.0.1:10002/devstoreaccount1"));

        assertEquals(devstoreAccount.getBlobStorageUri().getSecondaryUri(), new URI(
                "http://127.0.0.1:10000/devstoreaccount1-secondary"));
        assertEquals(devstoreAccount.getQueueStorageUri().getSecondaryUri(), new URI(
                "http://127.0.0.1:10001/devstoreaccount1-secondary"));
        assertEquals(devstoreAccount.getTableStorageUri().getSecondaryUri(), new URI(
                "http://127.0.0.1:10002/devstoreaccount1-secondary"));

        String devstoreAccountToStringWithSecrets = devstoreAccount.toString(true);
        CloudStorageAccount testAccount = CloudStorageAccount.parse(devstoreAccountToStringWithSecrets);

        AccountsAreEqual(testAccount, devstoreAccount);
        // Following should not throw exception:
        CloudStorageAccount.parse(devstoreAccountToStringWithSecrets);
    }

    @Test
    public void testCloudStorageAccountDefaultStorageAccountWithHttp() throws URISyntaxException, InvalidKeyException {
        StorageCredentialsAccountAndKey cred = new StorageCredentialsAccountAndKey(accountName, accountKey);
        CloudStorageAccount cloudStorageAccount = new CloudStorageAccount(cred, false);
        assertEquals(cloudStorageAccount.getBlobEndpoint(),
                new URI(String.format("http://%s.blob.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getQueueEndpoint(),
                new URI(String.format("http://%s.queue.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getTableEndpoint(),
                new URI(String.format("http://%s.table.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getFileEndpoint(),
                new URI(String.format("http://%s.file.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getBlobStorageUri().getSecondaryUri(),
                new URI(String.format("http://%s-secondary.blob.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getQueueStorageUri().getSecondaryUri(),
                new URI(String.format("http://%s-secondary.queue.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getTableStorageUri().getSecondaryUri(),
                new URI(String.format("http://%s-secondary.table.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getFileStorageUri().getSecondaryUri(),
                new URI(String.format("http://%s-secondary.file.core.windows.net", accountName)));

        String cloudStorageAccountToStringWithSecrets = cloudStorageAccount.toString(true);
        CloudStorageAccount testAccount = CloudStorageAccount.parse(cloudStorageAccountToStringWithSecrets);

        AccountsAreEqual(testAccount, cloudStorageAccount);
    }

    @Test
    public void testCloudStorageAccountDefaultStorageAccountWithHttps() throws URISyntaxException, InvalidKeyException {
        StorageCredentialsAccountAndKey cred = new StorageCredentialsAccountAndKey(accountName, accountKey);
        CloudStorageAccount cloudStorageAccount = new CloudStorageAccount(cred, true);
        assertEquals(cloudStorageAccount.getBlobEndpoint(),
                new URI(String.format("https://%s.blob.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getQueueEndpoint(),
                new URI(String.format("https://%s.queue.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getTableEndpoint(),
                new URI(String.format("https://%s.table.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getFileEndpoint(),
                new URI(String.format("https://%s.file.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getBlobStorageUri().getSecondaryUri(),
                new URI(String.format("https://%s-secondary.blob.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getQueueStorageUri().getSecondaryUri(),
                new URI(String.format("https://%s-secondary.queue.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getTableStorageUri().getSecondaryUri(),
                new URI(String.format("https://%s-secondary.table.core.windows.net", accountName)));
        assertEquals(cloudStorageAccount.getFileStorageUri().getSecondaryUri(),
                new URI(String.format("https://%s-secondary.file.core.windows.net", accountName)));

        String cloudStorageAccountToStringWithSecrets = cloudStorageAccount.toString(true);
        CloudStorageAccount testAccount = CloudStorageAccount.parse(cloudStorageAccountToStringWithSecrets);

        AccountsAreEqual(testAccount, cloudStorageAccount);
    }

    @Test
    public void testCloudStorageAccountConnectionStringRoundtrip() throws InvalidKeyException, URISyntaxException {
        String accountString1 = String.format("DefaultEndpointsProtocol=http;AccountName=%s;AccountKey=%s",
                accountName, accountKey);

        String accountString2 = String.format(
                "DefaultEndpointsProtocol=https;AccountName=%s;AccountKey=%s;QueueEndpoint=%s", accountName,
                accountKey, "https://alternate.queue.endpoint/");

        connectionStringRoundtripHelper(accountString1);
        connectionStringRoundtripHelper(accountString2);
    }

    private void connectionStringRoundtripHelper(String accountString) throws InvalidKeyException, URISyntaxException {
        CloudStorageAccount originalAccount = CloudStorageAccount.parse(accountString);
        String copiedAccountString = originalAccount.toString(true);
        //        assertEquals(accountString, copiedAccountString);
        CloudStorageAccount copiedAccount = CloudStorageAccount.parse(copiedAccountString);

        // make sure it round trips
        this.AccountsAreEqual(originalAccount, copiedAccount);
    }

    @Test
    public void testCloudStorageAccountClientMethods() throws URISyntaxException {
        StorageCredentialsAccountAndKey cred = new StorageCredentialsAccountAndKey(accountName, accountKey);

        CloudStorageAccount account = new CloudStorageAccount(cred, false);
        CloudBlobClient blob = account.createCloudBlobClient();
        CloudQueueClient queue = account.createCloudQueueClient();
        CloudTableClient table = account.createCloudTableClient();
        CloudFileClient file = account.createCloudFileClient();

        // check endpoints  
        assertEquals("Blob endpoint doesn't match account", account.getBlobEndpoint(), blob.getEndpoint());
        assertEquals("Queue endpoint doesn't match account", account.getQueueEndpoint(), queue.getEndpoint());
        assertEquals("Table endpoint doesn't match account", account.getTableEndpoint(), table.getEndpoint());
        assertEquals("File endpoint doesn't match account", account.getFileEndpoint(), file.getEndpoint());

        // check storage uris
        assertEquals("Blob endpoint doesn't match account", account.getBlobStorageUri(), blob.getStorageUri());
        assertEquals("Queue endpoint doesn't match account", account.getQueueStorageUri(), queue.getStorageUri());
        assertEquals("Table endpoint doesn't match account", account.getTableStorageUri(), table.getStorageUri());
        assertEquals("File endpoint doesn't match account", account.getFileStorageUri(), file.getStorageUri());

        // check creds
        assertEquals("Blob creds don't match account", account.getCredentials(), blob.getCredentials());
        assertEquals("Queue creds don't match account", account.getCredentials(), queue.getCredentials());
        assertEquals("Table creds don't match account", account.getCredentials(), table.getCredentials());
        assertEquals("File creds don't match account", account.getCredentials(), file.getCredentials());
    }

    @Test
    public void testCloudStorageAccountClientUriVerify() throws URISyntaxException, StorageException {
        StorageCredentialsAccountAndKey cred = new StorageCredentialsAccountAndKey(accountName, accountKey);
        CloudStorageAccount cloudStorageAccount = new CloudStorageAccount(cred, true);

        CloudBlobClient blobClient = cloudStorageAccount.createCloudBlobClient();
        CloudBlobContainer container = blobClient.getContainerReference("container1");
        assertEquals(cloudStorageAccount.getBlobEndpoint().toString() + "/container1", container.getUri().toString());

        CloudQueueClient queueClient = cloudStorageAccount.createCloudQueueClient();
        CloudQueue queue = queueClient.getQueueReference("queue1");
        assertEquals(cloudStorageAccount.getQueueEndpoint().toString() + "/queue1", queue.getUri().toString());

        CloudTableClient tableClient = cloudStorageAccount.createCloudTableClient();
        CloudTable table = tableClient.getTableReference("table1");
        assertEquals(cloudStorageAccount.getTableEndpoint().toString() + "/table1", table.getUri().toString());

        CloudFileClient fileClient = cloudStorageAccount.createCloudFileClient();
        CloudFileShare share = fileClient.getShareReference("share1");
        assertEquals(cloudStorageAccount.getFileEndpoint().toString() + "/share1", share.getUri().toString());
    }

    @Test
    public void testCloudStorageAccountParseNullEmpty() throws InvalidKeyException, URISyntaxException {
        // parse() should throw exception when passing in null or empty string
        try {
            CloudStorageAccount.parse(null);
            fail();
        }
        catch (IllegalArgumentException ex) {
            assertEquals(SR.INVALID_CONNECTION_STRING, ex.getMessage());
        }

        try {
            CloudStorageAccount.parse("");
            fail();
        }
        catch (IllegalArgumentException ex) {
            assertEquals(SR.INVALID_CONNECTION_STRING, ex.getMessage());
        }
    }

    @Test
    public void testCloudStorageAccountDevStoreNonTrueFails() throws InvalidKeyException, URISyntaxException {
        try {
            CloudStorageAccount.parse("UseDevelopmentStorage=false");
            fail();
        }
        catch (IllegalArgumentException ex) {
            assertEquals(SR.INVALID_CONNECTION_STRING_DEV_STORE_NOT_TRUE, ex.getMessage());
        }
    }

    @Test
    public void testCloudStorageAccountDevStorePlusAccountFails() throws InvalidKeyException, URISyntaxException {
        try {
            CloudStorageAccount.parse("UseDevelopmentStorage=false;AccountName=devstoreaccount1");
            fail();
        }
        catch (IllegalArgumentException ex) {
            assertEquals(SR.INVALID_CONNECTION_STRING_DEV_STORE_NOT_TRUE, ex.getMessage());
        }
    }

    @Test
    public void testCloudStorageAccountDevStorePlusEndpointFails() throws InvalidKeyException, URISyntaxException {
        try {
            CloudStorageAccount
                    .parse("UseDevelopmentStorage=false;BlobEndpoint=http://127.0.0.1:1000/devstoreaccount1");
            fail();
        }
        catch (IllegalArgumentException ex) {
            assertEquals(SR.INVALID_CONNECTION_STRING_DEV_STORE_NOT_TRUE, ex.getMessage());
        }
    }

    @Test
    public void testCloudStorageAccountDefaultEndpointOverride() throws URISyntaxException, InvalidKeyException {
        CloudStorageAccount account = CloudStorageAccount
                .parse("DefaultEndpointsProtocol=http;BlobEndpoint=http://customdomain.com/;AccountName=asdf;AccountKey=123=");

        assertEquals(new URI("http://customdomain.com/"), account.getBlobEndpoint());
        assertNull(account.getBlobStorageUri().getSecondaryUri());
    }

    @Test
    public void testCloudStorageAccountDevStore() throws URISyntaxException {
        // default
        CloudStorageAccount account = CloudStorageAccount.getDevelopmentStorageAccount();
        assertEquals(new URI("http://127.0.0.1:10000/devstoreaccount1"), account.getBlobEndpoint());
        assertEquals(new URI("http://127.0.0.1:10001/devstoreaccount1"), account.getQueueEndpoint());
        assertEquals(new URI("http://127.0.0.1:10002/devstoreaccount1"), account.getTableEndpoint());
        assertEquals(new URI("http://127.0.0.1:10000/devstoreaccount1-secondary"), account.getBlobStorageUri()
                .getSecondaryUri());
        assertEquals(new URI("http://127.0.0.1:10001/devstoreaccount1-secondary"), account.getQueueStorageUri()
                .getSecondaryUri());
        assertEquals(new URI("http://127.0.0.1:10002/devstoreaccount1-secondary"), account.getTableStorageUri()
                .getSecondaryUri());

        // proxy
        account = CloudStorageAccount.getDevelopmentStorageAccount(new URI("http://ipv4.fiddler"));
        assertEquals(new URI("http://ipv4.fiddler:10000/devstoreaccount1"), account.getBlobEndpoint());
        assertEquals(new URI("http://ipv4.fiddler:10001/devstoreaccount1"), account.getQueueEndpoint());
        assertEquals(new URI("http://ipv4.fiddler:10002/devstoreaccount1"), account.getTableEndpoint());
        assertEquals(new URI("http://ipv4.fiddler:10000/devstoreaccount1-secondary"), account.getBlobStorageUri()
                .getSecondaryUri());
        assertEquals(new URI("http://ipv4.fiddler:10001/devstoreaccount1-secondary"), account.getQueueStorageUri()
                .getSecondaryUri());
        assertEquals(new URI("http://ipv4.fiddler:10002/devstoreaccount1-secondary"), account.getTableStorageUri()
                .getSecondaryUri());
    }

    @Test
    public void testCloudStorageAccountDevStoreProxyUri() throws InvalidKeyException, URISyntaxException {
        CloudStorageAccount account = CloudStorageAccount
                .parse("UseDevelopmentStorage=true;DevelopmentStorageProxyUri=http://ipv4.fiddler");

        assertEquals(new URI("http://ipv4.fiddler:10000/devstoreaccount1"), account.getBlobEndpoint());
        assertEquals(new URI("http://ipv4.fiddler:10001/devstoreaccount1"), account.getQueueEndpoint());
        assertEquals(new URI("http://ipv4.fiddler:10002/devstoreaccount1"), account.getTableEndpoint());
        assertEquals(new URI("http://ipv4.fiddler:10000/devstoreaccount1-secondary"), account.getBlobStorageUri()
                .getSecondaryUri());
        assertEquals(new URI("http://ipv4.fiddler:10001/devstoreaccount1-secondary"), account.getQueueStorageUri()
                .getSecondaryUri());
        assertEquals(new URI("http://ipv4.fiddler:10002/devstoreaccount1-secondary"), account.getTableStorageUri()
                .getSecondaryUri());
    }

    @Test
    public void testCloudStorageAccountDevStoreRoundtrip() throws InvalidKeyException, URISyntaxException {
        String accountString = "UseDevelopmentStorage=true";

        assertEquals(accountString, CloudStorageAccount.parse(accountString).toString(true));
    }

    @Test
    public void testCloudStorageAccountDevStoreProxyRoundtrip() throws InvalidKeyException, URISyntaxException {
        String accountString = "UseDevelopmentStorage=true;DevelopmentStorageProxyUri=http://ipv4.fiddler/";

        assertEquals(accountString, CloudStorageAccount.parse(accountString).toString(true));
    }

    @Test
    public void testCloudStorageAccountDefaultCloudRoundtrip() throws InvalidKeyException, URISyntaxException {
        String accountString = "DefaultEndpointsProtocol=http;AccountName=test;AccountKey=abc=";

        assertEquals(accountString, CloudStorageAccount.parse(accountString).toString(true));
    }

    @Test
    public void testCloudStorageAccountExplicitCloudRoundtrip() throws InvalidKeyException, URISyntaxException {
        String accountString = "BlobEndpoint=https://blobs/;AccountName=test;AccountKey=abc=";

        assertEquals(accountString, CloudStorageAccount.parse(accountString).toString(true));
    }

    @Test
    public void testCloudStorageAccountAnonymousRoundtrip() throws InvalidKeyException, URISyntaxException {
        String accountString = "BlobEndpoint=http://blobs/";

        assertEquals(accountString, CloudStorageAccount.parse(accountString).toString(true));

        CloudStorageAccount account = new CloudStorageAccount(null, new StorageUri(new URI("http://blobs/")), null,
                null, null);

        AccountsAreEqual(account, CloudStorageAccount.parse(account.toString(true)));
    }

    @Test
    public void testCloudStorageAccountEmptyValues() throws InvalidKeyException, URISyntaxException {
        String accountString = ";BlobEndpoint=http://blobs/;;AccountName=test;;AccountKey=abc=;";
        String validAccountString = "BlobEndpoint=http://blobs/;AccountName=test;AccountKey=abc=";

        assertEquals(validAccountString, CloudStorageAccount.parse(accountString).toString(true));
    }

    @Test
    public void testCloudStorageAccountJustBlobToString() throws InvalidKeyException, URISyntaxException {
        String accountString = "BlobEndpoint=http://blobs/;AccountName=test;AccountKey=abc=";

        assertEquals(accountString, CloudStorageAccount.parse(accountString).toString(true));
    }

    @Test
    public void testCloudStorageAccountJustQueueToString() throws InvalidKeyException, URISyntaxException {
        String accountString = "QueueEndpoint=http://queue/;AccountName=test;AccountKey=abc=";

        assertEquals(accountString, CloudStorageAccount.parse(accountString).toString(true));
    }

    @Test
    public void testCloudStorageAccountJustTableToString() throws InvalidKeyException, URISyntaxException {
        String accountString = "TableEndpoint=http://table/;AccountName=test;AccountKey=abc=";

        assertEquals(accountString, CloudStorageAccount.parse(accountString).toString(true));
    }

    @Test
    public void testCloudStorageAccountJustFileToString() throws InvalidKeyException, URISyntaxException {
        String accountString = "FileEndpoint=http://file/;AccountName=test;AccountKey=abc=";

        assertEquals(accountString, CloudStorageAccount.parse(accountString).toString(true));
    }

    @Test
    public void testCloudStorageAccountExportKey() throws InvalidKeyException, URISyntaxException {
        String accountKeyString = "abc2564=";
        String accountString = "BlobEndpoint=http://blobs/;AccountName=test;AccountKey=" + accountKeyString;
        CloudStorageAccount account = CloudStorageAccount.parse(accountString);
        StorageCredentialsAccountAndKey accountAndKey = (StorageCredentialsAccountAndKey) account.getCredentials();
        String key = accountAndKey.getCredentials().getKey().getBase64EncodedKey();
        assertEquals(accountKeyString, key);

        byte[] keyBytes = accountAndKey.getCredentials().exportKey();
        byte[] expectedKeyBytes = Base64.decode(accountKeyString);
        assertArrayEquals(expectedKeyBytes, keyBytes);
    }
}
