package ru.practicum.explorewithmemain.participation;

import org.springframework.stereotype.Service;

@Service
public class ParticipationService {

    /**
     * Requests a participation in event.
     */
    public void requestParticipation(long userId, long eventId) {
        /*

    нельзя добавить повторный запрос
    инициатор события не может добавить запрос на участие в своём событии
    нельзя участвовать в неопубликованном событии
    если у события достигнут лимит запросов на участие - необходимо вернуть ошибку
    если для события отключена пре-модерация запросов на участие, то запрос должен автоматически перейти в состояние подтвержденного

         */
    }

    /**
     * Cancels self participation request of a user.
     */
    private void cancelParticipationRequest(long userId, long requestId) {

    }

    /**
     * Accepts a participation request from the user.
     */
    public void acceptParticipationRequest(long userId, long eventId, long requestId) {
        /*

    нельзя подтвердить заявку, если уже достигнут лимит по заявкам на данное событие
    если при подтверждении данной заявки, лимит заявок для события исчерпан, то все неподтверждённые заявки необходимо отклонить

         */
    }

    /**
     * Rejects a participation request from the user.
     */
    public void rejectParticipationRequest(long userId, long eventId, long requestId) {

    }
}
