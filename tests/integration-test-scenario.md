# Integration Test Scenario

## Scenario
Run the complete SDA-Pro console demo from alert ingestion to incident response.

## Steps
1. Compile the project.
2. Run `MainDemo`.
3. Verify that alerts are pulled from at least two sources.
4. Verify that alerts are normalized into canonical alerts.
5. Verify composite grouping into an alert campaign or incident cluster.
6. Verify enrichment pipeline execution.
7. Verify incident creation and lifecycle state transitions.
8. Verify response orchestration through facade, strategy, factory, decorator, and proxy.
9. Verify event publishing and observer updates.
10. Verify final demo output is saved in `demo-output.txt`.

## Expected Result
The console demo completes without errors and clearly shows the full flow:

`Ingest Alert → Normalize → Group → Enrich → Classify → Create Incident → Respond → Publish Events → Dashboard/Audit Updates`
