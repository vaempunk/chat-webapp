
function messageReducer(state, action) {
  if (action.type === "SEND") {
    if (action.data.error === null) return { textError: null };
    const textError = action.data.details
      .filter((d) => d.key === "text")
      .map((d) => d.value)
      .join(", ");
    return { textError };
  }

  return state;
}

export default messageReducer;
