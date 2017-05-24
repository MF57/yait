import {UPVOTE_TOPIC} from "../actions/types/TopicActionTypes";
import {preloadState} from "../index";


export function topicReducer(state, action) {
    if (state === undefined) {
        return preloadState
    }

    switch (action.type) {
        case UPVOTE_TOPIC: {
            return upvoteTopic(action.id, state);
        }
    }
    return state;
};

let upvoteTopic = function (id, topics) {
    let newTopics = [];
    for (let i = 0; i < topics.length; i++) {
        if (topics[i].id === id) {
            newTopics.push(Object.assign({}, topics[i], {score: topics[i].score + 1}));
        } else {
            newTopics.push(topics[i]);
        }
    }
    return newTopics;
};