import {REPLACE_TOPICS, UPVOTE_TOPIC} from "./types/TopicActionTypes";

export function upvoteTopic(id) {
    return {
        type: UPVOTE_TOPIC,
        id: id
    };
}

export function replaceTopics(newTopics) {
    return {
        type: REPLACE_TOPICS,
        topics: newTopics
    }
}