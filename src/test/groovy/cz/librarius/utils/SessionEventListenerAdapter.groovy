package cz.librarius.utils

import org.hibernate.SessionEventListener

/**
 * Adapter class with default NO-OP implementation for all {@link SessionEventListener} methods.
 */
class SessionEventListenerAdapter implements SessionEventListener {

    @Override
    void transactionCompletion(boolean successful) {
    }

    @Override
    void jdbcConnectionAcquisitionStart() {
    }

    @Override
    void jdbcConnectionAcquisitionEnd() {
    }

    @Override
    void jdbcConnectionReleaseStart() {
    }

    @Override
    void jdbcConnectionReleaseEnd() {
    }

    @Override
    void jdbcPrepareStatementStart() {
    }

    @Override
    void jdbcPrepareStatementEnd() {
    }

    @Override
    void jdbcExecuteStatementStart() {
    }

    @Override
    void jdbcExecuteStatementEnd() {
    }

    @Override
    void jdbcExecuteBatchStart() {
    }

    @Override
    void jdbcExecuteBatchEnd() {
    }

    @Override
    void cachePutStart() {
    }

    @Override
    void cachePutEnd() {
    }

    @Override
    void cacheGetStart() {
    }

    @Override
    void cacheGetEnd(boolean hit) {
    }

    @Override
    void flushStart() {
    }

    @Override
    void flushEnd(int numberOfEntities, int numberOfCollections) {
    }

    @Override
    void partialFlushStart() {
    }

    @Override
    void partialFlushEnd(int numberOfEntities, int numberOfCollections) {
    }

    @Override
    void dirtyCalculationStart() {
    }

    @Override
    void dirtyCalculationEnd(boolean dirty) {
    }

    @Override
    void end() {
    }

}
