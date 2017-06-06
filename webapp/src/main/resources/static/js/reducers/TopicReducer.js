import {REPLACE_TOPICS, UPVOTE_TOPIC} from "../actions/types/TopicActionTypes";
import {preloadState} from "../index";


export function topicReducer(state, action) {
    if (state === undefined) {
        return preloadState
    }

    switch (action.type) {
        case UPVOTE_TOPIC: {
            return upvoteTopic(action.id, action.score, state);
        }
        case REPLACE_TOPICS: {
            return replaceTopics(action.topics)
        }
    }
    return state;
}

let replaceTopics = function (newTopics) {
    console.log(newTopics);
    console.log("Replace topics");
    return newTopics
};

let upvoteTopic = function (id, score, topics) {
    let newTopics = [];
    for (let i = 0; i < topics.length; i++) {
        if (topics[i].id === id) {
            newTopics.push(Object.assign({}, topics[i], {score: topics[i].score + parseInt(score)}));
        } else {
            newTopics.push(topics[i]);
        }
    }
    return newTopics;
};
