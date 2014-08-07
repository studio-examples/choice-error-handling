package org.mule.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;
import org.mule.transport.NullPayload;

public class ErrorHandlingTest extends FunctionalTestCase
{
	private static final String MESSAGE = "{\n \"email\": \"aaa@aaa.aa\", \n \"item name\": \"aa\", \n  \"item units\": 10, \n\"item price per unit\": 1,\n \"membership\": \"free\"\n}";
	private static final String MESSAGE_WRONG = "{\n \"item name\": \"aa\", \n  \"item units\": 10, \n\"item price per unit\": 1,\n \"membership\": \"free\"\n}";
	private static final String REPLY = "Input data validation passed.";
	private static final String REPLY_WRONG = "Missing input data: {item name=aa, membership=free, item price per unit=1, item units=10}";
    @Override
    protected String getConfigResources()
    {
        return "choice-error-handling.xml";
    }

    @Test
    public void inputValidationTest() throws Exception
    {
        MuleClient client = new MuleClient(muleContext);
        Map<String, Object> props = new HashMap<String, Object>();
        props.put("http.method", "POST");
        MuleMessage result = client.send("http://localhost:8081/", MESSAGE, props);
        assertNotNull(result);
        assertFalse(result.getPayload() instanceof NullPayload);
        assertEquals(REPLY, result.getPayloadAsString());
        
        result = client.send("http://localhost:8081/", MESSAGE_WRONG, props);
        assertNotNull(result);
        assertEquals("400", result.getInboundProperty("http.status"));        
        assertEquals(REPLY_WRONG, result.getPayloadAsString());
        
    }

}
