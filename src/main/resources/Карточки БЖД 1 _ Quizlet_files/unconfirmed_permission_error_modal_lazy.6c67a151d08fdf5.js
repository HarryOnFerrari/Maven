(window.QJP=window.QJP||[]).push([["unconfirmed_permission_error_modal_lazy"],{chz3:function(e,a,s){"use strict";s.r(a),s.d(a,"default",(function(){return h}));s("4mDm"),s("3bBZ");var n=s("nrsj"),r=s("qqKc"),t=s("t1Ez"),i=s("JxFz"),o=s("Pxp3"),l=s("E/Xc"),d=s("dxfC"),m=s("xisi"),c=s("q1tI"),_=s.n(c);class h extends c.Component{constructor(){super(...arguments),this.state={isResendingConfirmation:!1,wasEmailResent:!1},this.getHeaderContent=()=>this.state.wasEmailResent?this.props.isUnderageUser?_.a.createElement(t.a,{id:"classes.page_header_new.permission_error_modal.email_confirmation_sent_header"}):_.a.createElement(t.a,{id:"classes.page_header_new.permission_error_modal.check_inbox_header"}):this.props.headerContent,this.getSubheaderContent=()=>this.state.wasEmailResent?this.props.isUnderageUser?_.a.createElement(t.a,{id:"classes.page_header_new.permission_error_modal.email_confirmation_sent_underage_subheader"}):_.a.createElement(t.a,{id:"classes.page_header_new.permission_error_modal.email_confirmation_sent_subheader"}):this.props.subheaderContent,this.onEmailResendSuccess=()=>this.setState({wasEmailResent:!0}),this.handleSendEmailConfirmation=()=>{this.setState({isResendingConfirmation:!0}),m.a.post("/settings/resend-confirmation").credentials().end(this.onEmailResendSuccess)}}renderEmailCtas(){var e=this.state.isResendingConfirmation,a=this.props.googleClassroomModalHandler;return _.a.createElement(o.a,{className:"UnconfirmedPermissionErrorModal-Ctas"},_.a.createElement(l.a,{"aria-label":Object(n.a)("classes.page_header_new.permission_error_modal.resend_email_confirmation_cta"),disabled:e,loading:e,onClick:this.handleSendEmailConfirmation,width:"fill"},_.a.createElement(t.a,{id:"classes.page_header_new.permission_error_modal.resend_email_confirmation_cta"})),this.props.googleClassroomModalHandler?_.a.createElement("div",null,_.a.createElement(r.d,{isFullWidth:!0,onClick:a,text:Object(n.a)("classes.page_header_new.permission_error_modal.connect_google_classroom_cta")})):null,this.props.googleClassroomModalHandler||this.props.isUnderageUser?null:_.a.createElement("div",null,_.a.createElement(i.a,{"aria-label":Object(n.a)("classes.page_header_new.permission_error_modal.update_email_address_cta"),onClick:()=>{window.location.href="/settings#email-change-setting"},variant:"basic",width:"fill"},_.a.createElement(t.a,{id:"classes.page_header_new.permission_error_modal.update_email_address_cta"}))))}renderEmailResendSuccess(){return _.a.createElement(o.a,{className:"UnconfirmedPermissionErrorModal-emailSentImage"})}render(){return _.a.createElement(d.a,{headerContent:this.getHeaderContent(),includeCloseButton:!0,isOpen:this.props.isOpen,onClose:this.props.onClose},_.a.createElement(o.a,null,this.getSubheaderContent()),this.state.wasEmailResent?this.renderEmailResendSuccess():this.renderEmailCtas())}}}}]);