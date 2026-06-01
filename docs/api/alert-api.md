# Alert Ingestion API Contract

## Purpose
This contract describes how external security sources submit alerts to SDA-Pro.

## Endpoint
`POST /alerts/ingest`

## Request Body
```json
{
  "sourceType": "SPLUNK",
  "rawMessage": "Failed login detected from suspicious IP",
  "sourceIp": "203.0.113.45",
  "destinationIp": "172.16.4.15",
  "username": "finance.admin",
  "timestamp": "2026-05-29T10:30:00Z"
}
```

## Response Body
```json
{
  "alertId": "ALERT-1001",
  "sourceType": "SPLUNK",
  "severity": "HIGH",
  "status": "NORMALIZED",
  "message": "Alert ingested and normalized successfully"
}
```

## Notes
- Splunk and Firewall inputs are different externally, but both are converted into `CanonicalAlert`.
- Adapter Pattern handles external source format differences.
- Factory Method selects the correct normalizer.

## Related Code
- `SplunkAdapter`
- `FirewallAdapter`
- `AlertNormalizerFactory`
- `CanonicalAlert`
