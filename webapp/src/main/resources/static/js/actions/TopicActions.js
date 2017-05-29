import {REPLACE_TOPICS, UPVOTE_TOPIC} from "./types/TopicActionTypes";

export function upvoteTopic(id, points) {
    return {
        type: UPVOTE_TOPIC,
        id: id,
        points: points
    };
}

export function replaceTopics(newTopics) {
    return {
        type: REPLACE_TOPICS,
        topics: newTopics
    }
}
