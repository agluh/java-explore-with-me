package ru.practicum.explorewithmemain.event;

public class EventService {

    /**
     * Adds a new event.
     *
     * @throws UserIsNotActiveException if current user is not activate
     * @throws TooLittleTimeLeftBeforeEventStartException if less than 2 hours left before an event
     */
    public void addEvent() {

    }

    /**
     * Updates an event.
     *
     * @throws UserIsNotActiveException if current user is not activate
     * @throws TooLittleTimeLeftBeforeEventStartException if less than 2 hours left before an event
     * @throws EventIsNotAbleToChangeException if event is not canceled or not await of moderation
     * @throws EventNotFoundException in case event not found
     */
    public void updateEvent() {

    }

    /**
     * Cancel an event.
     *
     * @throws UserIsNotActiveException if current user is not activate
     * @throws EventIsNotAbleToChangeException if event is not await of moderation
     * @throws EventNotFoundException in case event not found
     */
    public void cancelEvent() {

    }

    /**
     * Publishes an event.
     *
     * @throws EventNotFoundException in case event not found
     */
    public void publishEvent(long eventId) {

        /*

    дата начала события должна быть не ранее чем за час от даты публикации.
    событие должно быть в состоянии ожидания публикации

         */
    }

    /**
     * Rejects event publication.
     *
     * @throws EventNotFoundException in case event not found
     */
    public void rejectEvent(long eventId) {
        /*
        событие не должно быть опубликовано.
         */
    }
}
