import {REPLACE_TOPICS, UPVOTE_TOPIC} from "./types/TopicActionTypes";

export function upvoteTopic(id, score) {
    return {
        type: UPVOTE_TOPIC,
        id: id,
        score: score
    };
}

export function replaceTopics(newTopics) {
    return {
        type: REPLACE_TOPICS,
        topics: newTopics
    }
}
