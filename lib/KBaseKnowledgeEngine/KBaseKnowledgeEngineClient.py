# -*- coding: utf-8 -*-
############################################################
#
# Autogenerated by the KBase type compiler -
# any changes made here will be overwritten
#
############################################################

from __future__ import print_function
# the following is a hack to get the baseclient to import whether we're in a
# package or not. This makes pep8 unhappy hence the annotations.
try:
    # baseclient and this client are in a package
    from .baseclient import BaseClient as _BaseClient  # @UnusedImport
except:
    # no they aren't
    from baseclient import BaseClient as _BaseClient  # @Reimport


class KBaseKnowledgeEngine(object):

    def __init__(
            self, url=None, timeout=30 * 60, user_id=None,
            password=None, token=None, ignore_authrc=False,
            trust_all_ssl_certificates=False,
            auth_svc='https://kbase.us/services/authorization/Sessions/Login'):
        if url is None:
            raise ValueError('A url is required')
        self._service_ver = None
        self._client = _BaseClient(
            url, timeout=timeout, user_id=user_id, password=password,
            token=token, ignore_authrc=ignore_authrc,
            trust_all_ssl_certificates=trust_all_ssl_certificates,
            auth_svc=auth_svc)

    def getConnectorsStatus(self, context=None):
        """
        :returns: instance of list of type "ConnectorStatus" (state - one of
           queued, started, finished, error. output - either empty for
           queued/started states or error message for error state or output
           message for finished.) -> structure: parameter "user" of String,
           parameter "obj_ref" of String, parameter "obj_type" of String,
           parameter "connector_app" of String, parameter "connector_title"
           of String, parameter "job_id" of String, parameter "state" of
           String, parameter "output" of String, parameter "new_re_nodes" of
           Long, parameter "updated_re_nodes" of Long, parameter
           "new_re_links" of Long, parameter "queued_epoch_ms" of Long,
           parameter "started_epoch_ms" of Long, parameter
           "finished_epoch_ms" of Long
        """
        return self._client.call_method(
            'KBaseKnowledgeEngine.getConnectorsStatus',
            [], self._service_ver, context)

    def cleanConnectorErrors(self, context=None):
        return self._client.call_method(
            'KBaseKnowledgeEngine.cleanConnectorErrors',
            [], self._service_ver, context)

    def getAppsStatus(self, context=None):
        """
        :returns: instance of list of type "AppStatus" (state - one of none,
           queued, started, finished, error. output - either empty for
           queued/started states or error message for error state or output
           message for finished.) -> structure: parameter "user" of String,
           parameter "app" of String, parameter "app_title" of String,
           parameter "job_id" of String, parameter "state" of String,
           parameter "output" of String, parameter "new_re_nodes" of Long,
           parameter "updated_re_nodes" of Long, parameter "new_re_links" of
           Long, parameter "queued_epoch_ms" of Long, parameter
           "started_epoch_ms" of Long, parameter "finished_epoch_ms" of Long,
           parameter "scheduled_epoch_ms" of Long
        """
        return self._client.call_method(
            'KBaseKnowledgeEngine.getAppsStatus',
            [], self._service_ver, context)

    def runApp(self, params, context=None):
        """
        Execute KE-App.
        :param params: instance of type "RunAppParams" (app - name of
           registered KB-SDK module configured to be compatible with KE.
           ref_mode - flag for public reference data processing (accessible
           only for admins).) -> structure: parameter "app" of String,
           parameter "ref_mode" of type "boolean" (A boolean. 0 = false,
           other = true.)
        :returns: instance of type "RunAppOutput" -> structure: parameter
           "job_id" of String
        """
        return self._client.call_method(
            'KBaseKnowledgeEngine.runApp',
            [params], self._service_ver, context)

    def getConnectorState(self, params, context=None):
        """
        :param params: instance of type "GetConnectorStateParams" ->
           structure: parameter "obj_ref" of String
        :returns: instance of String
        """
        return self._client.call_method(
            'KBaseKnowledgeEngine.getConnectorState',
            [params], self._service_ver, context)

    def cleanAppData(self, params, context=None):
        """
        Only admins can run this function.
        :param params: instance of type "CleanAppDataParams" -> structure:
           parameter "app" of String
        """
        return self._client.call_method(
            'KBaseKnowledgeEngine.cleanAppData',
            [params], self._service_ver, context)

    def status(self, context=None):
        return self._client.call_method('KBaseKnowledgeEngine.status',
                                        [], self._service_ver, context)
